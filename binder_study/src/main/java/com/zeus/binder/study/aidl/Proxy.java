package com.zeus.binder.study.aidl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class Proxy implements ILeoAidl {
    private IBinder mRemote;

    Proxy(IBinder remote) {
        mRemote = remote;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }

    public String getInterfaceDescriptor() {
        return Stub.DESCRIPTOR;
    }

    @Override
    public void setName(String name) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken(Stub.DESCRIPTOR);
            _data.writeString(name);
            mRemote.transact(Stub.TRANSACTION_setName, _data, _reply, 0);
            _reply.readException();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override
    public String getName() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        String _result;
        try {
            _data.writeInterfaceToken(Stub.DESCRIPTOR);

            mRemote.transact(Stub.TRANSACTION_getName, _data, _reply, 0);
            _reply.readException();
            _result = _reply.readString();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }
}