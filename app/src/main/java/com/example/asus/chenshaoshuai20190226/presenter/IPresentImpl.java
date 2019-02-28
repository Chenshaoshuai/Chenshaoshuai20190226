package com.example.asus.chenshaoshuai20190226.presenter;

import com.example.asus.chenshaoshuai20190226.callback.MyCallBack;
import com.example.asus.chenshaoshuai20190226.model.IModelImpl;
import com.example.asus.chenshaoshuai20190226.view.IView;

public class IPresentImpl implements IPresent {
    private IView iView;
    private IModelImpl iModel;

    public IPresentImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    @Override
    public void getRequest(String url, Class clazz) {
          iModel.getRequest(url, clazz, new MyCallBack() {
              @Override
              public void onSuccess(Object data) {
                  iView.onSuccess(data);
              }
          });
    }
    public void onDetach(){
        if(iModel!=null){
            iModel=null;
        }else if(iView!=null){
            iView=null;
        }
    }
}
