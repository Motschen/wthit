package mcp.mobius.waila.addons.ic2;

import mcp.mobius.waila.Waila;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.WailaPlugin;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;

@WailaPlugin("IC2")
public class PluginIC2 implements IWailaPlugin {

    public static Class<?> TileBaseGenerator = null;
    public static Field TileBaseGenerator_storage = null;
    public static Field TileBaseGenerator_maxStorage = null;
    public static Field TileBaseGenerator_production = null;

    public static Class<?> TileGeoGenerator = null;
    public static Field TileGeoGenerator_storage = null;
    public static Field TileGeoGenerator_maxStorage = null;
    public static Field TileGeoGenerator_production = null;

    public static Class<?> TileKineticGenerator = null;
    public static Field TileKineticGenerator_storage = null;
    public static Field TileKineticGenerator_maxStorage = null;
    public static Field TileKineticGenerator_production = null;

    public static Class<?> TileSemifluidGenerator = null;
    public static Field TileSemifluidGenerator_storage = null;
    public static Field TileSemifluidGenerator_maxStorage = null;
    public static Field TileSemifluidGenerator_production = null;

    public static Class<?> TileStirlingGenerator = null;
    public static Field TileStirlingGenerator_storage = null;
    public static Field TileStirlingGenerator_maxStorage = null;
    public static Field TileStirlingGenerator_production = null;

    @Override
    public void register(IWailaRegistrar registrar) {
        // XXX : We register the Energy interface first
        try {
            TileBaseGenerator = Class.forName("ic2.core.block.generator.tileentity.TileEntityBaseGenerator");
            TileBaseGenerator_storage = TileBaseGenerator.getDeclaredField("storage");
            TileBaseGenerator_maxStorage = TileBaseGenerator.getDeclaredField("maxStorage");
            TileBaseGenerator_production = TileBaseGenerator.getDeclaredField("production");

            TileGeoGenerator = Class.forName("ic2.core.block.generator.tileentity.TileEntityGeoGenerator");
            TileGeoGenerator_storage = TileGeoGenerator.getDeclaredField("storage");
            TileGeoGenerator_maxStorage = TileGeoGenerator.getDeclaredField("maxStorage");
            TileGeoGenerator_production = TileGeoGenerator.getDeclaredField("production");

            TileKineticGenerator = Class.forName("ic2.core.block.generator.tileentity.TileEntityKineticGenerator");
            TileKineticGenerator_storage = TileKineticGenerator.getDeclaredField("EUstorage");
            TileKineticGenerator_maxStorage = TileKineticGenerator.getDeclaredField("maxEUStorage");
            TileKineticGenerator_production = TileKineticGenerator.getDeclaredField("production");
            TileKineticGenerator_storage.setAccessible(true);
            TileKineticGenerator_maxStorage.setAccessible(true);
            TileKineticGenerator_production.setAccessible(true);

            TileSemifluidGenerator = Class.forName("ic2.core.block.generator.tileentity.TileEntitySemifluidGenerator");
            TileSemifluidGenerator_storage = TileSemifluidGenerator.getDeclaredField("storage");
            TileSemifluidGenerator_maxStorage = TileSemifluidGenerator.getDeclaredField("maxStorage");
            TileSemifluidGenerator_production = TileSemifluidGenerator.getDeclaredField("production");
            TileSemifluidGenerator_storage.setAccessible(true);
            TileSemifluidGenerator_maxStorage.setAccessible(true);
            TileSemifluidGenerator_production.setAccessible(true);

            TileStirlingGenerator = Class.forName("ic2.core.block.generator.tileentity.TileEntityStirlingGenerator");
            TileStirlingGenerator_storage = TileStirlingGenerator.getDeclaredField("EUstorage");
            TileStirlingGenerator_maxStorage = TileStirlingGenerator.getDeclaredField("maxEUStorage");
            TileStirlingGenerator_production = TileStirlingGenerator.getDeclaredField("production");

            //registrar.addConfigRemote("Thermal Expansion", "thermalexpansion.energyhandler");
            registrar.registerBodyProvider(new HUDHandlerTEGenerator(), TileBaseGenerator);
            registrar.registerBodyProvider(new HUDHandlerTEGenerator(), TileGeoGenerator);
            registrar.registerBodyProvider(new HUDHandlerTEGenerator(), TileKineticGenerator);
            registrar.registerBodyProvider(new HUDHandlerTEGenerator(), TileSemifluidGenerator);
            registrar.registerBodyProvider(new HUDHandlerTEGenerator(), TileStirlingGenerator);

            registrar.registerNBTProvider(new HUDHandlerTEGenerator(), TileBaseGenerator);
            registrar.registerNBTProvider(new HUDHandlerTEGenerator(), TileGeoGenerator);
            registrar.registerNBTProvider(new HUDHandlerTEGenerator(), TileKineticGenerator);
            registrar.registerNBTProvider(new HUDHandlerTEGenerator(), TileSemifluidGenerator);
            registrar.registerNBTProvider(new HUDHandlerTEGenerator(), TileStirlingGenerator);

            registrar.addConfigRemote("IndustrialCraft2", "ic2.storage");
            registrar.addConfigRemote("IndustrialCraft2", "ic2.outputeu");

        } catch (Exception e) {
            Waila.LOGGER.log(Level.WARN, "[IndustrialCraft 2] Error while loading generator hooks." + e);
        }
    }

}
