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

    }

    abstract class centerUserRepostory extends Repository<centerUserImodel> {

    }

    abstract class centetUserRepostory extends Presenter<centerUserRepostory,CenterUserIview> {

        public centetUserRepostory(CenterUserIview centerUserIview) {
            super(centerUserIview);
        }
    }
}
