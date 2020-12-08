package com.shopmall.bawei.shopmall1805;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.deom.BaseActivity;
import com.bawei.deom.addPage.AddCountroller;
import com.bawei.deom.addPage.AddImpl;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.user.ShangPing;

@Route(path = "/mainactivity/XIangqingActivity")
public class XIangqingActivity extends BaseActivity<AddImpl,AddCountroller.AddView> implements AddCountroller.AddView {
    private ImageView image;
    private TextView text;
    private TextView price;
    private Button pop;
    private TextView gouwu;
    private int size=0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_xiangqing;
    }

    @Override
    protected void inPresone() {
      prine=new AddImpl();
    }

    @Override
    protected void inData() {

    }

    @Override
    protected void intView() {
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        price = (TextView) findViewById(R.id.price);
        pop = (Button) findViewById(R.id.pop);
        gouwu = (TextView) findViewById(R.id.gouwu);
        ARouter.getInstance().inject(
                this
        );
              Intent intent=getIntent();
      final ShangPing  shangp= (ShangPing) intent.getSerializableExtra("shangp");
        Glide.with(this).load("http://49.233.0.68:8080/atguigu/img/"+shangp.getUrl()).into(image);
          text.setText(shangp.getProductName());
          price.setText(shangp.getProductPrice());
          pop.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  final PopupWindow popupWindow=new PopupWindow();
                 View view= LayoutInflater.from(XIangqingActivity.this).inflate(R.layout.pop,null);
                 popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                 popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                ImageView pic = view.findViewById(R.id.pic);
                Glide.with(XIangqingActivity.this).load("http://49.233.0.68:8080/atguigu/img/"+shangp.getUrl()).into(pic);
                TextView name = view.findViewById(R.id.name);
                name.setText(shangp.getProductName()+"");
                TextView yuan = view.findViewById(R.id.yuan);
                yuan.setText(shangp.getProductPrice()+"");
                final TextView num = view.findViewById(R.id.num);
                num.setText(""+size);

                Button jian=view.findViewById(R.id.jian);
                jian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (size==0){
                            num.setText("0");
                            Toast.makeText(XIangqingActivity.this, "不能减了", Toast.LENGTH_SHORT).show();
                        }else {

                            size--;
                            num.setText(""+size);

                        }
                    }
                });
                Button jia=view.findViewById(R.id.jia);
                jia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (size==99){
                            num.setText(""+99);
                            Toast.makeText(XIangqingActivity.this, "不能加了", Toast.LENGTH_SHORT).show();
                        }else {
                                      size++;
                              num.setText(""+size);
                        }
                    }
                });
                Button no=view.findViewById(R.id.no);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                Button yes=view.findViewById(R.id.yes);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                popupWindow.setContentView(view);

                 popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
              }
          });
        gouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XIangqingActivity.this, "1111", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/fragment/ShoppingFragment").navigation();
            }
        });

    }

    @Override
    public void CheckOneProductInventoryView(String productNum) {

    }

    @Override
    public void AddShoppingView(String addResult) {

    }

    @Override
    public void UpdateProductNumView(String result) {

    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }


//     ShangPing shangp;
//    int size=1;
//    DaoSession daoSession;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xiangqing);

//

//        daoSession=((ShopmallApplication)getApplication()).getDaoSession();

//        Intent intent=getIntent();
//         shangp= (ShangPing) intent.getSerializableExtra("shangp");
//        Toast.makeText(XIangqingActivity.this, ""+shangp.toString(), Toast.LENGTH_SHORT).show();
//        Glide.with(this).load("http://49.233.0.68:8080/atguigu/img/"+shangp.getPic()).into(image);
//        text.setText(""+shangp.getName());
//        price.setText("￥"+shangp.getPrice());
//        pop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

}
