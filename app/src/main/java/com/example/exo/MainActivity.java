package com.example.exo;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.ExoPlayer;

import com.example.exo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String mediaUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4";
    private ExoPlayer player;
    private ActivityMainBinding binding;

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.playerView.initializeDatabaseProvider();
        binding.playerView.initializeActivityResult(getActivityResultRegistry());

        player = new ExoPlayer.Builder(this).build();
        binding.playerView.setPlayer(player);

        player.setMediaSource(binding.playerView.EnableCaching(mediaUrl));
        player.prepare();
    }

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onResume() {
        super.onResume();
        binding.playerView.onResumeActivity();
    }

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onPause() {
        super.onPause();
        binding.playerView.onPauseActivity();
    }

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding.playerView.onDestroyActivity();
        if (player != null) {
            binding.playerView.setPlayer(null);
            player.release();
        }
        this.binding = null;
    }

    @OptIn(markerClass = UnstableApi.class)
    @Override
    public void onUserLeaveHint() {
        binding.playerView.onUserLeaveHintActivity();
    }

    @OptIn(markerClass = UnstableApi.class)
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        binding.playerView.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }

    @OptIn(markerClass = UnstableApi.class)
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        binding.playerView.onConfigurationChangedActivity(newConfig);
    }
}
