package com.example.wanandroid.ui.fragment.five.child;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.ui.activity.LoginActivity;
import com.example.wanandroid.util.MyGlideEngine;
import com.google.gson.JsonObject;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.HashSet;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment {

    private static final int REQUEST_CODE_CHOOSE = 23;
    CircleImageView imageHead;
    TextView userName;
    List<Uri> result;

    public static MineFragment getInstance(){
        Bundle bundle = new Bundle();
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initUI(View view) {
        imageHead = view.findViewById(R.id.image_head);
        userName = view.findViewById(R.id.tv_username);
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] m = new int[256];
                String s = "abadafag";
                int res = 0, left = 0;

                for (int i = 0; i < s.length(); i++) {
                    Log.d("hao","===== " + s.charAt(i));
                    Log.d("hao","===== " + m[s.charAt(i)]);
                    Log.d("hao","=========== ");
                    left = Math.max(left, m[s.charAt(i)]);

                    res = Math.max(res, i - left + 1);

                    m[s.charAt(i)] = i + 1;
                }
//                int res = 0, left = 0, right = 0;
//                HashSet<Character> t = new HashSet<Character>();
//
//                while (right < s.length()) {
//                    if (!t.contains(s.charAt(right))) {
//                        t.add(s.charAt(right));
//                        right++;
//                        res = Math.max(res, t.size());
//                    } else {
//                        t.remove(s.charAt(left));
//                        left++;
//                    }
//                }
                Toast.makeText(getActivity(),res+"",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),SearchActivity.class));
            }
        });
        imageHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
//                Matisse.from(MineFragment.this)
//                        .choose(MimeType.allOf())//图片类型
//                        .countable(false)//true:选中后显示数字;false:选中后显示对号
//                        .maxSelectable(5)//可选的最大数
//                        .capture(false)//选择照片时，是否显示拍照
//                        .captureStrategy(new CaptureStrategy(true, "com.example.wanandroid.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
//                        .imageEngine(new MyGlideEngine())//图片加载引擎
//                        .forResult(REQUEST_CODE_CHOOSE);//
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK){
            result = Matisse.obtainResult(data);
            userName.setText(result.toString());
            for (Uri uri: result) {
                Glide.with(getContext()).load(uri).into(imageHead);
            }
        }
    }

    @Override
    protected void initData() {
    }
}
