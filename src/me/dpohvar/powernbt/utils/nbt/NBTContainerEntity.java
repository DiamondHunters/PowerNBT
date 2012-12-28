package me.dpohvar.powernbt.utils.nbt;

import me.dpohvar.powernbt.utils.versionfix.XNBTBase;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.Arrays;
import java.util.List;

import static me.dpohvar.powernbt.utils.versionfix.StaticValues.*;
import static me.dpohvar.powernbt.utils.versionfix.VersionFix.*;

public class NBTContainerEntity extends NBTContainer {

    Entity ent;

    public NBTContainerEntity(Entity ent) {
        this.ent = ent;
    }

    @Override
    public Entity getObject() {
        return ent;
    }

    @Override
    public List<String> getTypes() {
        if (ent instanceof LivingEntity) return Arrays.asList("entity", "living", ent.getType().name());
        return Arrays.asList("entity", ent.getType().name());
    }

    @Override
    public XNBTBase getRootBase() {
        Object liv = callMethod(ent, "getHandle", noInput);
        XNBTBase base = getShell(XNBTBase.class, getNew(classNBTTagCompound, noInput));
        callMethod(liv, "b", oneNBTTagCompound, base);
        return base;
    }

    @Override
    public void setRootBase(XNBTBase base) {
        Object liv = callMethod(ent, "getHandle", noInput);
        callMethod(liv, "a", oneNBTTagCompound, base);

    }

    @Override
    public String getName() {
        return ent.getType().getName() + " (id" + ent.getEntityId() + ")";
    }
}
