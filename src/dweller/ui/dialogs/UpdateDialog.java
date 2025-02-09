package dweller.ui.dialogs;

import arc.scene.ui.Dialog;
import arc.util.Align;
import mindustry.gen.Icon;
import mindustry.gen.Tex;
import mindustry.graphics.Pal;
import mindustry.ui.Styles;

public class UpdateDialog extends Dialog {
    public Runnable updater;

    public UpdateDialog(){
        super("");

        cont.add("@dialog.dweller-update.name", Styles.defaultLabel, 1).padLeft(4).center();
        cont.row();
        cont.image(Tex.whiteui, Pal.heal).growX().height(5).pad(5).padTop(8).width(500).align(Align.center);
        cont.row();
        cont.add("@dialog.dweller-update.description").width(500).wrap().pad(10).get().setAlignment(Align.center, Align.center);
        cont.row();

        cont.table(t -> {
            t.button("@back", Icon.left, this::hide).size(200, 54).pad(8).padTop(6).align(Align.center);
            t.button("@update", Icon.download, () -> updater.run()).size(200, 54).pad(8).padTop(6).align(Align.center);
        });
    }

    public void open(Runnable e){
        updater = e;
        show();
    }
}