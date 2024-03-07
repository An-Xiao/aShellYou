package in.hridayan.ashell.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.badge.BadgeDrawable;
import in.hridayan.ashell.R;
import in.hridayan.ashell.fragments.aShellFragment;
import in.hridayan.ashell.fragments.otgFragment;

public class aShellActivity extends AppCompatActivity {
  private BottomNavigationView mNav;
  private View mContentView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ashell);

    mContentView = findViewById(android.R.id.content);
    mNav = findViewById(R.id.bottom_nav_bar);

    mNav.setVisibility(View.VISIBLE);

    mContentView
        .getViewTreeObserver()
        .addOnGlobalLayoutListener(
            new ViewTreeObserver.OnGlobalLayoutListener() {
              @Override
              public void onGlobalLayout() {
                int heightDiff = mContentView.getRootView().getHeight() - mContentView.getHeight();
                if (heightDiff > 200) {
                  mNav.setVisibility(View.GONE);
                } else {
                  mNav.setVisibility(View.VISIBLE);
                }
              }
            });

    BadgeDrawable badge = mNav.getOrCreateBadge(R.id.nav_otgShell);
    badge.setVisible(true);
    badge.setText("Beta");
    mNav.setOnItemSelectedListener(
        item -> {
          switch (item.getItemId()) {
            case R.id.nav_localShell:
              if (!(getSupportFragmentManager().findFragmentById(R.id.fragment_container)
                  instanceof aShellFragment)) {
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new aShellFragment())
                    .commit();
              }
              return true;
            case R.id.nav_otgShell:
              if (!(getSupportFragmentManager().findFragmentById(R.id.fragment_container)
                  instanceof otgFragment)) {
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new otgFragment())
                    .commit();
              }
              return true;
            default:
              return false;
          }
        });
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_container, new aShellFragment())
        .commit();
  }
}
