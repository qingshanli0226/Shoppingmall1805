package framework;


import framework.Mvp.Imodel;
import framework.Mvp.Iview;
import framework.Mvp.Presenter;
import framework.Mvp.Repository;

public
interface Contact {
    interface CenterUserIview extends Iview {

    }

    interface centerUserImodel extends Imodel {
         void gethomeurl();
         void getshopcal(int count);
         void getBiaoCal();
    }

    abstract class centerUserRepostory extends Repository<centerUserImodel> {
         public abstract void getHomeur();
         public abstract void getshopcal(int count);
         public abstract void getBaiocal();

    }

    abstract class centetUserpepostory extends Presenter<centerUserRepostory,CenterUserIview> {

        public centetUserpepostory(CenterUserIview centerUserIview) {
            super(centerUserIview);
        }
        public abstract void getHomeurl(Userc userc);
        public abstract   void getshopcal(int count,User2 userc);
        public abstract void getBaiocal(User3 user3);
    }
}
