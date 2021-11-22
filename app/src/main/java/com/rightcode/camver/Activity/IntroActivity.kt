package com.rightcode.camver.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.Util.PreferenceUtil
import com.rightcode.camver.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity<ActivityIntroBinding>() {
    var mHandler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_intro)
    }

    override fun initActivity() {
        mHandler = Handler()
        mHandler!!.postDelayed({
            val Token: String = PreferenceUtil.getInstance(mContext).get(PreferenceUtil.PreferenceKey.Token, "")
            if (Token === "" || Token == null) {
                Log.d(Token)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent) //다음 액티비티 이동
                finish()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent) //다음 액티비티 이동
                finish()
            }
        }, 4000) //2000은 2초를 의미한다.
    }

    override fun initClickListener() {}
}