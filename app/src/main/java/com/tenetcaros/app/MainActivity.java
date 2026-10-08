package com.tenetcaros.app;

import android.app.*;
import android.content.*;
import android.graphics.Color;
import android.net.Uri;
import android.os.*;
import android.provider.Settings;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    private int dp(int v){ return (int)(v*getResources().getDisplayMetrics().density); }
    private TextView text(String s,int sp,int color){ TextView t=new TextView(this); t.setText(s); t.setTextSize(sp); t.setTextColor(color); t.setGravity(Gravity.CENTER); t.setPadding(dp(8),dp(8),dp(8),dp(8)); return t; }
    private Button app(String title,String pkg,boolean driveSafe){ Button b=new Button(this); b.setText(title + (driveSafe?"":"\nтолько стоянка")); b.setEnabled(driveSafe); if(driveSafe) b.setOnClickListener(v->{ Intent i=getPackageManager().getLaunchIntentForPackage(pkg); if(i!=null){ ActivityOptions o=ActivityOptions.makeBasic().setLaunchDisplayId(getDisplay()!=null?getDisplay().getDisplayId():0); startActivity(i,o.toBundle()); } else Toast.makeText(this,"Приложение не установлено",Toast.LENGTH_SHORT).show();}); return b; }
    @Override public void onCreate(Bundle b){ super.onCreate(b); setShowWhenLocked(true); LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(16),dp(16),dp(16),dp(16)); root.setBackgroundColor(Color.rgb(11,14,18)); root.addView(text("TENET Car OS 1.0",28,Color.WHITE)); int id=getDisplay()!=null?getDisplay().getDisplayId():0; root.addView(text("Display ID: "+id+" • SAFE MODE\nPARKED_ONLY заблокирован до доверенного сигнала PARKED",14,Color.rgb(255,176,32))); GridLayout g=new GridLayout(this); g.setColumnCount(3); String[][] apps={{"Яндекс Навигатор","ru.yandex.yandexnavi"},{"Яндекс Музыка","ru.yandex.music"},{"Spotify","com.spotify.music"},{"Google Maps","com.google.android.apps.maps"},{"2ГИС","ru.dublgis.dgismobile"},{"Android Auto","com.google.android.projection.gearhead"}}; for(String[] a:apps){ Button x=app(a[0],a[1],true); g.addView(x,new ViewGroup.LayoutParams(dp(260),dp(110))); } Button youtube=app("YouTube","com.google.android.youtube",false); g.addView(youtube,new ViewGroup.LayoutParams(dp(260),dp(110))); Button browser=app("Браузер","com.android.chrome",false); g.addView(browser,new ViewGroup.LayoutParams(dp(260),dp(110))); Button settings=app("Настройки","",false); g.addView(settings,new ViewGroup.LayoutParams(dp(260),dp(110))); root.addView(g,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1)); TextView diag=text("Диагностика: Android "+Build.VERSION.SDK_INT+" • "+Build.MODEL+" • "+Build.DEVICE,13,Color.LTGRAY); root.addView(diag); setContentView(root); }
}
