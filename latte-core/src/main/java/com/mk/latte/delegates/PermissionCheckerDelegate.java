package com.mk.latte.delegates;

import android.Manifest;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.mk.latte.ui.camera.LatteCamera;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 权限验证
 * Created by lenovo on 2017/10/16.
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    //不是直接调用的方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        LatteCamera.start(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult
                (this, requestCode, grantResults);

    }


    /**
     * 这个是真正调用的方法
     */
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithCheck(this);
    }


    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        Toast.makeText(getContext(), "永久拒绝权限", Toast.LENGTH_LONG).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }


    private void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }



}

