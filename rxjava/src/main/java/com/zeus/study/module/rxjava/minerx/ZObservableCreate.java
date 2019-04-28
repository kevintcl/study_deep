package com.zeus.study.module.rxjava.minerx;

import java.util.concurrent.atomic.AtomicReference;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public class ZObservableCreate<T> extends ZObservable<T> {

    private ZObservableOnSubscribe<T> source;


    public ZObservableCreate(ZObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(ZObserver<? super T> observer) {
        CreateEmitter<T> emitter = new CreateEmitter<>(observer);
        observer.onSubscribe(emitter);

        try {
            source.subscribe(emitter);

        } catch (Throwable e) {
            if (e instanceof VirtualMachineError) {
                throw (Error) e;//TODO
            }
            emitter.onError(e);
        }
    }

    static final class CreateEmitter<T>
            extends AtomicReference<ZDisposable>
            implements ZObservableEmitter<T>, ZDisposable {

        final ZObserver<? super T> observer;

        CreateEmitter(ZObserver<? super T> observer) {
            this.observer = observer;
        }


        @Override
        public void dispose() {
            boolean dispose = ZDisposableHelper.dispose(this);
        }

        @Override
        public boolean isDisposed() {
            return ZDisposableHelper.isDisposed(this.get());
        }

        @Override
        public void onNext(T msg) {
            if (msg == null) {
                onError(new Exception("onNext msg is Null value, not allowed!!"));
            } else {
                if (!isDisposed()) {
                    observer.onNext(msg);
                }
            }
        }

        @Override
        public void onError(Throwable throwable) {
            if (throwable == null) {
                throwable = new Exception("onError throwable is Null value, not allowed!!");
            }

            if (!isDisposed()) {
                try {
                    observer.onError(throwable);
                } finally {
                    dispose();
                }
            }
        }

        @Override
        public void onComplete() {
            if (!isDisposed()) {
                observer.onComplete();
            }
        }
    }
}
