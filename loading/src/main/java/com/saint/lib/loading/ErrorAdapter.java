
package com.saint.lib.loading;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


/**
 * @author Saint
 */
public class ErrorAdapter extends LoadingHelper.Adapter<ErrorAdapter.ViewHolder> {

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new ViewHolder(inflater.inflate(R.layout.loading_layout_error, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder) {
    holder.layoutEmpty.setOnClickListener(v -> {
      if (holder.getOnReloadListener() != null) {
        holder.getOnReloadListener().onReload();
      }
    });
  }

  public static class ViewHolder extends LoadingHelper.ViewHolder {

    private final View layoutEmpty;

    ViewHolder(@NonNull View rootView) {
      super(rootView);
      layoutEmpty = rootView.findViewById(R.id.layout_empty);
    }
  }
}
