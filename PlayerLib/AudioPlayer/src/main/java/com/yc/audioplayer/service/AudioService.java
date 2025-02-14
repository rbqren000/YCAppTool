package com.yc.audioplayer.service;

import android.content.Context;

import com.yc.audioplayer.bean.AudioPlayData;
import com.yc.audioplayer.bean.TtsPlayerConfig;
import com.yc.audioplayer.inter.PlayStateListener;
import com.yc.audioplayer.manager.AudioManager;
import com.yc.easyexecutor.DelegateTaskExecutor;

/**
 * <pre>
 *     @author yangchong
 *     email  : yangchong211@163.com
 *     GitHub : https://github.com/yangchong211/YCVideoPlayer
 *     time  : 2018/8/6
 *     desc  : provider接口具体代理类
 *     revise:
 * </pre>
 */
public final class AudioService implements AudioServiceProvider {

    private AudioServiceProvider mDelegate;
    private TtsPlayerConfig mConfig;
    public static final String TAG = "AudioService: ";

    private AudioService() {
    }

    public static AudioService getInstance() {
        return Singleton.INSTANCE;
    }

    @Override
    public final void init(final Context context , TtsPlayerConfig config) {
        mConfig = config;
        if (mConfig.getIsTtsDeque()){
            mDelegate = new AudioServiceImpl1();
        } else {
            mDelegate = new AudioServiceImpl2();
        }
        this.mDelegate.init(context,config);
    }

    @Override
    public final boolean isInit() {
        return null != this.mDelegate && this.mDelegate.isInit();
    }

    @Override
    public TtsPlayerConfig getConfig() {
        if (mConfig == null){
            mConfig = new TtsPlayerConfig.Builder().build();
        }
        return mConfig;
    }

    @Override
    public final void stop() {
        if (null != this.mDelegate) {
            if (DelegateTaskExecutor.getInstance().isMainThread()){
                mDelegate.stop();
            } else {
                DelegateTaskExecutor.getInstance().postToMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mDelegate.stop();
                    }
                });
            }
        }
    }

    @Override
    public final void pause() {
        if (null != this.mDelegate) {
            if (DelegateTaskExecutor.getInstance().isMainThread()){
                mDelegate.pause();
            } else {
                DelegateTaskExecutor.getInstance().postToMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mDelegate.pause();
                    }
                });
            }
        }
    }

    @Override
    public final void resume() {
        if (null != this.mDelegate) {
            if (DelegateTaskExecutor.getInstance().isMainThread()){
                mDelegate.resume();
            } else {
                DelegateTaskExecutor.getInstance().postToMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mDelegate.resume();
                    }
                });
            }
        }
    }

    @Override
    public final void release() {
        if (null != this.mDelegate) {
            if (DelegateTaskExecutor.getInstance().isMainThread()){
                mDelegate.release();
            } else {
                DelegateTaskExecutor.getInstance().postToMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mDelegate.release();
                    }
                });
            }
        }
    }

    @Override
    public final boolean isPlaying() {
        return null != this.mDelegate && this.mDelegate.isPlaying();
    }

    @Override
    public final void play(final AudioPlayData audioPlayData) {
        if (null != this.mDelegate) {
            if (DelegateTaskExecutor.getInstance().isMainThread()){
                this.mDelegate.play(audioPlayData);
            } else {
                DelegateTaskExecutor.getInstance().postToMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mDelegate.play(audioPlayData);
                    }
                });
            }
        }
    }

    @Override
    public final void playTts(final String tts) {
        if (null != this.mDelegate) {
            if (DelegateTaskExecutor.getInstance().isMainThread()){
                this.mDelegate.playTts(tts);
            } else {
                DelegateTaskExecutor.getInstance().postToMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mDelegate.playTts(tts);
                    }
                });
            }
        }
    }

    @Override
    public void playUrl(String url) {
        if (null != this.mDelegate) {
            if (DelegateTaskExecutor.getInstance().isMainThread()){
                this.mDelegate.playUrl(url);
            } else {
                DelegateTaskExecutor.getInstance().postToMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mDelegate.playUrl(url);
                    }
                });
            }
        }
    }

    @Override
    public final void playAudioResource(final int rawId) {
        if (null != this.mDelegate) {
            if (DelegateTaskExecutor.getInstance().isMainThread()){
                this.mDelegate.playAudioResource(rawId);
            } else {
                DelegateTaskExecutor.getInstance().postToMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mDelegate.playAudioResource(rawId);
                    }
                });
            }
        }
    }

    @Override
    public final void setPlayStateListener(final PlayStateListener arg0) {
        if (null != this.mDelegate) {
            this.mDelegate.setPlayStateListener(arg0);
        }
    }

    private static final class Singleton {
        static final AudioService INSTANCE = new AudioService();
    }
}
