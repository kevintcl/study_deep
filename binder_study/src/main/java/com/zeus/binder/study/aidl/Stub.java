package com.zeus.binder.study.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Local-side IPC implementation stub class.
 */
public abstract class Stub extends Binder implements ILeoAidl {
    public static final String DESCRIPTOR = "com.zeus.binder.study.fileILeoAidl";

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
        return new Proxy(obj);
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


}
