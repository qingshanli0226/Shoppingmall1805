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
         void getshopcal(int count);

    }

    abstract class centerUserRepostory extends Repository<centerUserImodel> {
         public abstract void getshopcal(int count);

    }

    abstract class centetUserpepostory extends Presenter<centerUserRepostory,CenterUserIview> {

        public centetUserpepostory(CenterUserIview centerUserIview) {
            super(centerUserIview);
        }
        public abstract   void getshopcal(int count,User2 userc);
    }
}
