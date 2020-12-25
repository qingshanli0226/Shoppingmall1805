package framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import framework.messagegreendao.DaoMaster;
import framework.messagegreendao.ShopMessageGreenBean;
import framework.messagegreendao.ShopMessageGreenBeanDao;

/**
 * 数据库单例
 */
public
class MessageMangerUlis {
    private static MessageMangerUlis instance;
    private Context alpplicationContext;
    private final String MMU_NAME  = "DB_NAME";//数据库名
    private final String MESSAGE_SP_NAME = "shopcarCountSp";//sp存储文件名
    private final String MESSAGE_SP_COUNT = "shopcarCount";//sp存储文件的类名
    private ShopMessageGreenBeanDao messageGreenBeanDao;
    private Handler handler = new Handler();

    private SharedPreferences messageSharedPreferences;
    private SharedPreferences.Editor messageEditor;
    //创建一个全局线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();
    public static MessageMangerUlis getInstance(){
        if (instance == null){
            synchronized (MessageMangerUlis.class){
                if (instance == null){
                    instance = new MessageMangerUlis();
                }
            }
        }
        return instance;
    }

    public  void init(Context context){
        this.alpplicationContext = context;
        //初始化数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(alpplicationContext, MMU_NAME);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        messageGreenBeanDao = daoMaster.newSession().getShopMessageGreenBeanDao();

        messageSharedPreferences = alpplicationContext.getSharedPreferences(MESSAGE_SP_NAME,Context.MODE_PRIVATE);
        messageEditor = messageSharedPreferences.edit();

    }
    //sp存储文件 存入消息的数量
    public void setWriteMessage(int count){
        messageEditor.putInt(MESSAGE_SP_COUNT,count);
        messageEditor.commit();
    }

    public int getReadMessage(){
        return messageSharedPreferences.getInt(MESSAGE_SP_COUNT,0);
    }

    //使用线程池来做存储数据，不能在主线程中操作数据里
    public void addMessage(@NotNull final ShopMessageGreenBean shopMessageGreenBean,final IMessageListener iMessageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                messageGreenBeanDao.insert(shopMessageGreenBean);
                setWriteMessage(getReadMessage()+1);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (iMessageListener!=null){
                            iMessageListener.OnResult(true,null);
                        }
                    }
                });
            }
        });
    }

    public void deleteMessage(@NotNull final ShopMessageGreenBean shopMessageGreenBean, final IMessageListener iMessageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                messageGreenBeanDao.delete(shopMessageGreenBean);
                if (getReadMessage()>0){
                    setWriteMessage(getReadMessage()-1);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (iMessageListener!=null){
                            iMessageListener.OnResult(true,null);
                        }
                    }
                });
            }
        });
    }

    public void updataMessage(@NotNull final  ShopMessageGreenBean shopMessageGreenBean,final IMessageListener iMessageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                messageGreenBeanDao.update(shopMessageGreenBean);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iMessageListener.OnResult(true,null);
                    }
                });
            }
        });
    }

    public void queryMessage(@NotNull final  IMessageListener iMessageListener){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final List<ShopMessageGreenBean> list = messageGreenBeanDao.queryBuilder()
                        .orderDesc(ShopMessageGreenBeanDao.Properties.Time).limit(50).list();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iMessageListener.OnResult(true,list);
                    }
                });
            }
        });
    }

    public interface IMessageListener{
        void OnResult(boolean isSuccess, List<ShopMessageGreenBean> shopMessageGreenBeans);
    }
}
