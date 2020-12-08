package view.adaper;

import android.support.annotation.Nullable;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mode.ShopcarBean;

public class ShopAdaper extends BaseQuickAdapter<ShopcarBean, BaseViewHolder> {
    private boolean isEditMode;
    public ShopAdaper(int layoutResId, @Nullable List<ShopcarBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopcarBean item) {

    }

    public void setEditMode(boolean clickChange) {
        this.isEditMode = clickChange;
        Toast.makeText(mContext, "当前点击是"+isEditMode, Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
    }

}
