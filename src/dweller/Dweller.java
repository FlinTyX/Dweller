package dweller;

import static mindustry.Vars.*;

import arc.Core;
import arc.Events;
import arc.util.Http;
import arc.util.serialization.Jval;
import dweller.ui.dialogs.UpdateDialog;
import mindustry.game.EventType.*;
import mindustry.mod.Mod;
import mindustry.mod.Mods;

public class Dweller extends Mod {
    public Dweller(){
        super();
    }

    @Override
    public void init(){
        if(!headless){
            Events.on(ClientLoadEvent.class, e -> checkReleases());
        }
    }

    public void checkReleases(){
        Mods.ModMeta meta = mods.locateMod("dweller").meta;

        Http.get(ghApi + "/repos/" + meta.name + "/releases", res -> {
            var json = Jval.read(res.getResultAsString());
            Jval release = json.asArray().get(0);

            String curv = release.getString("name");

            if(true /*!meta.version.equals(curv)*/){
                Core.app.post(() -> {
                    var dialog = new UpdateDialog();
                    dialog.open(() -> {
                        String releaseUrl = release.getString("url");
                        ui.mods.githubImportMod(meta.repo, meta.java, releaseUrl.substring(releaseUrl.lastIndexOf("/") + 1));
                    });
                });
            }
        });
    }
}
