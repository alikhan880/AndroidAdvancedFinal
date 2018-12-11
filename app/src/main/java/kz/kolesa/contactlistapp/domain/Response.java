package kz.kolesa.contactlistapp.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author marshal@kolesa.kz
 */
public class Response<T> {

    @Nullable
    private T mResult;
    @Nullable
    private Throwable mException;

    public Response(@NonNull T result) {
        mResult = result;
    }

    public Response(@NonNull Throwable exception) {
        mException = exception;
    }

    public boolean isSuccess() {
        return mException == null;
    }

    @Nullable
    public T getResult() {
        return mResult;
    }

    @Nullable
    public Throwable getException() {
        return mException;
    }
}