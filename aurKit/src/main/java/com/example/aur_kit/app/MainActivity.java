package com.example.aur_kit.app;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.core.Mat;

import com.example.aur_kit.R;
import com.example.aur_kit.IA.GraphNode;
import com.example.aur_kit.IA.StartSearch;
import com.example.aur_kit.utils.CalculateDirection;
import com.example.aur_kit.utils.DecodeQR;
import com.example.aur_kit.utils.QRtoClassMap;
import com.example.aur_kit.utils.ResultQR;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements CvCameraViewListener2 {

	// Logging
	private final String TAG = getClass().getCanonicalName();

	// Camera View
	private CameraBridgeViewBase mOpenCvCameraView;

	private Timer updateTimer;
	final Handler myHandler = new Handler();

	private ImageView direction;
	private Button btn1;
	private Button btn2;
	private ImageView map;

	private Mat mRgba = null;

	private ArrayList<GraphNode> route;
	private QRtoClassMap qrm;
	private int contRoute;
	private Drawable dir;

	// Load and initialise OpenCV
	private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
		@Override
		public void onManagerConnected(int status) {
			switch (status) {
			case LoaderCallbackInterface.SUCCESS:
				Log.i(TAG, "OpenCV loaded successfully");
				mOpenCvCameraView.enableView();
				break;

			default:
				super.onManagerConnected(status);
				break;
			}
		}
	};

	public MainActivity() {
		Log.i(TAG, "Instantiated new " + this.getClass());
	}

	// Called when the activity is first created
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "called onCreate");
		super.onCreate(savedInstanceState);

		// Don't turn off the screen
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		setContentView(R.layout.activity_main);
		final ViewFlipper vf = (ViewFlipper) findViewById(R.id.viewFlipper);
		updateTimer = new Timer();

		direction = (ImageView) findViewById(R.id.direction);
		map = (ImageView) findViewById(R.id.map_view);
		btn1 = (Button) findViewById(R.id.button_view);

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (map.getDrawable() == null) {
					Toast toast = Toast.makeText(MainActivity.this,
							getResources().getString(R.string.no_map),
							Toast.LENGTH_LONG);
					toast.show();
				} else
					vf.showNext();
			}
		});

		btn2 = (Button) findViewById(R.id.button_view_1);

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				vf.showPrevious();
			}
		});

		route = new ArrayList<GraphNode>();
		contRoute = 0;
		dir = null;

		initiateOpenCvCameraView();

	}

	private void initiateOpenCvCameraView() {
		mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.main_view);
		mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
		mOpenCvCameraView
				.setCvCameraViewListener((CvCameraViewListener2) MainActivity.this);

		qrm = new QRtoClassMap(MainActivity.this);

		final Bundle bundle = this.getIntent().getExtras();
		final String destination = bundle
				.getString("DESTINATION");
		final boolean elevator = bundle.getBoolean("ELEVATOR");
		mOpenCvCameraView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				try {
					ResultQR qr = DecodeQR.zxing(mRgba);

					if (qr != null) {
						if (route.isEmpty()) {
							

							route = StartSearch.calculateRoute(qr.getCode(),
									qrm.getQrClassMap().get(destination).getCode(), elevator,
									MainActivity.this);
						}
						GraphNode nodoact = route.get(contRoute);
						if (qr.getCode().equals(
								String.valueOf(nodoact.getPunto()))) {
							if (contRoute == route.size() - 1) {

								dir = CalculateDirection.calculateDirectionClass(
										MainActivity.this,
										qrm.classOrientation(destination), qr
												.getOrientation());

								TimerTask task = new TimerTask() {
									@Override
									public void run() {
										finish();
									}
								};

								Timer timer = new Timer();
								timer.schedule(task, 2000);
							} else
								dir = CalculateDirection.calculateDirectionQR(
										MainActivity.this,
										route.get(contRoute),
										route.get(contRoute + 1),
										qr.getOrientation(), 
										elevator);
							contRoute++;
						} else {
							dir = getResources().getDrawable(R.drawable.mal);
							contRoute--;
						}
						updateScreen(qr.getCode(), dir);
					} else {
						Toast toast = Toast.makeText(MainActivity.this,
								getResources().getString(R.string.no_qr),
								Toast.LENGTH_SHORT);
						toast.show();
					}
				} catch (ChecksumException e) {
					e.printStackTrace();
				} catch (FormatException e) {
					e.printStackTrace();
				}
				return false;
			}
		});
	}

	@Override
	public void onPause() {
		Log.i(TAG, "called onPause");
		super.onPause();
		if (mOpenCvCameraView != null)
			mOpenCvCameraView.disableView();
	}

	@Override
	public void onResume() {
		Log.i(TAG, "called onResume");
		super.onResume();
		OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0 , this,
				mLoaderCallback);
	}

	public void onDestroy() {
		Log.i(TAG, "called onDestroy");
		super.onDestroy();
		if (mOpenCvCameraView != null)
			mOpenCvCameraView.disableView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onCameraViewStarted(int width, int height) {

	}

	public void onCameraViewStopped() {

	}

	public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
		mRgba = inputFrame.rgba();
		return mRgba;
	}

	public void updateScreen(String qr, Drawable ruta) {

		// load the floor map or load the new one if it has changed
		String floor = qr.substring(0, 1);
		switch (floor) {
		case "0":
			map.setImageDrawable(getResources().getDrawable(R.drawable.floor_0));
			break;
		case "1":
			map.setImageDrawable(getResources().getDrawable(R.drawable.floor_1));
			break;
		case "2":
			map.setImageDrawable(getResources().getDrawable(R.drawable.floor_2));
			break;
		case "3":
			map.setImageDrawable(getResources().getDrawable(R.drawable.floor_3));
			break;
		case "4":
			map.setImageDrawable(getResources().getDrawable(R.drawable.floor_4));
			break;
		case "5":
			map.setImageDrawable(getResources().getDrawable(R.drawable.floor_5));
			break;
		default:
			break;
		}

		direction.setImageDrawable(ruta);

		final Runnable myRunnable = new Runnable() {
			public void run() {
				direction.setImageDrawable(null);
			}
		};

		updateTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				UpdateGUI();
			}

			private void UpdateGUI() {
				myHandler.post(myRunnable);
			}
		}, 2000);
	}
}
