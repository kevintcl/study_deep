/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/tangchunlin/study/projects/study_deep/binder_study/src/main/aidl/com/zeus/binder/study/ILeoAidl.aidl
 */
package com.zeus.binder.study.file;
// Declare any non-default types here with import statements

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ILeoAidl extends IInterface {

    public void setName(String name) throws RemoteException;

    public String getName() throws RemoteException;


    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends Binder implements ILeoAidl {
        private static final String DESCRIPTOR = "com.zeus.binder.study.fileILeoAidl";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an ILeoAidl interface,
         * generating a proxy if needed.
         */
        public static ILeoAidl asInterface(IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);

            if (((iin != null) && (iin instanceof ILeoAidl))) {
                return ((ILeoAidl) iin);
            }
            return new ILeoAidl.Stub.Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        static final int TRANSACTION_setName = (IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getName = (IBinder.FIRST_CALL_TRANSACTION + 1);

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_setName: {
                    data.enforceInterface(descriptor);
                    String _arg0;
                    _arg0 = data.readString();
                    this.setName(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getName: {
                    data.enforceInterface(descriptor);
                    String _result = this.getName();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements ILeoAidl {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public void setName(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(name);
                    mRemote.transact(ILeoAidl.Stub.TRANSACTION_setName, _data, _reply, 0);
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
                    _data.writeInterfaceToken(DESCRIPTOR);

                    mRemote.transact(ILeoAidl.Stub.TRANSACTION_getName, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }


    }

}
