package com.purpleprint.betterplayerrespawn.gui;

import static org.objectweb.asm.Opcodes.*;

import java.util.Iterator;
import java.util.function.Function;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.resources.I18n;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

@SuppressWarnings("unused")
public class GuiGameOverTransformer implements IClassTransformer {

    private static final boolean isDeobf = FMLLaunchHandler.isDeobfuscatedEnvironment();

    // GuiGameOver
    private static final String internal$GuiGameOver = isDeobf ? "net/minecraft/client/gui/GuiGameOver" : "bkv";

    // GuiButton
    private static final String internal$GuiButton = isDeobf ? "net/minecraft/client/gui/GuiButton" : "bja";

    // Class name
    // replace the class with your IFMLLoadingPlugin implemented class, make sure to correct path to this ASM class
    private static final String internal$Hook = TheFairyIsCookingSomething.class.getPackage().getName().replace('.', '/') + "GuiGameOverTransformer$Hook";

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null) return null;

        if (!transformedName.equals("net.minecraft.client.gui.GuiGameOver")) {
            return basicClass;
        }

        final ClassReader classReader = new ClassReader(basicClass);
        final ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);

        // Logger, uncomment and point this to the IFMLLoadingPlugin implemented class
//        RetakePlugin.LOGGER.info("Patching class {}", transformedName);
//
//        RetakePlugin.LOGGER.debug("Implementing interface {} to {}", internal$Hook, transformedName);

        classNode.interfaces.add(internal$Hook);

        int patched = 0;

        // method checking
        for (MethodNode mn : classNode.methods) {
            if (Utils.String$isAnyEqual(mn.name, "initGui", "func_73866_w_", "b")
                    && mn.desc.equals("()V")
            ) {
                patched += PatchingMethod.Patch$initGui.patchMethod(mn);
                continue;
            }

            if (Utils.String$isAnyEqual(mn.name, "actionPerformed", "func_146284_a", "a")
                    && Utils.String$isAnyEqual(mn.desc, "(Lnet/minecraft/client/gui/GuiButton;)V", "(Lbja;)V")
            ) {
                patched += PatchingMethod.Patch$actionPerformed.patchMethod(mn);
                continue;
            }

            if (Utils.String$isAnyEqual(mn.name, "drawScreen", "func_73863_a", "a")
                    && mn.desc.equals("(IIF)V")
            ) {
                patched += PatchingMethod.Patch$drawScreen.patchMethod(mn);
            }
        }

        // only apply patches if all sufficient
        if (patched == PatchingMethod.values().length) {
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(classWriter);
            // Logger, uncomment and point this to the IFMLLoadingPlugin implemented class
//            RetakePlugin.LOGGER.info("Successful patched class {}", transformedName);
            return classWriter.toByteArray();
        }

        // Logger, uncomment and point this to the IFMLLoadingPlugin implemented class
//        RetakePlugin.LOGGER.info("Failed patching class {}", transformedName);
        return basicClass;
    }

    private enum PatchingMethod {
        Patch$initGui(visitor -> {
            int ordinal = 4;

            Iterator<AbstractInsnNode> insnNodeIterator = visitor.instructions.iterator();
            AbstractInsnNode insn;
            while(insnNodeIterator.hasNext()) {
                insn = insnNodeIterator.next();
                if (!(insn instanceof MethodInsnNode)) continue;
                MethodInsnNode methodInsn = (MethodInsnNode) insn;
                if (methodInsn.getOpcode() == INVOKEINTERFACE
                        && methodInsn.owner.contains("java/util/List")
                        && methodInsn.name.contains("add")
                        && --ordinal == 0
                ) {
                    // implementation
                    InsnList instList = new InsnList();
                    instList.add(new VarInsnNode(ALOAD, 0));
                    instList.add(new VarInsnNode(ALOAD, 0));
                    instList.add(new MethodInsnNode(INVOKEVIRTUAL, internal$GuiGameOver, "BetterPlayerRespawn$hook$initGui", "(" + Utils.ASM$asObjectDesc(internal$GuiGameOver) + ")V", false));

                    // insert
                    visitor.instructions.insert(methodInsn.getNext(), instList);

                    return true;
                }
            }

            return false;
        }),

        Patch$actionPerformed(visitor -> {
            Iterator<AbstractInsnNode> insnNodeIterator = visitor.instructions.iterator();
            AbstractInsnNode insn;
            while(insnNodeIterator.hasNext()) {
                insn = insnNodeIterator.next();
                if (insn.getOpcode() == RETURN) {
                    // implementation
                    InsnList instList = new InsnList();
                    instList.add(new VarInsnNode(ALOAD, 0));
                    instList.add(new VarInsnNode(ALOAD, 0));
                    instList.add(new VarInsnNode(ALOAD, 1));
                    instList.add(new MethodInsnNode(INVOKEVIRTUAL,
                            internal$GuiGameOver,
                            "BetterPlayerRespawn$hook$actionPerformed",
                            "(" + Utils.ASM$asObjectDesc(internal$GuiGameOver, internal$GuiButton) + ")V",
                            false));

                    // insert
                    visitor.instructions.insertBefore(insn, instList);

                    return true;
                }
            }

            return false;
        }),

        Patch$drawScreen(visitor -> {
            Iterator<AbstractInsnNode> insnNodeIterator = visitor.instructions.iterator();
            AbstractInsnNode insn;
            while(insnNodeIterator.hasNext()) {
                insn = insnNodeIterator.next();
                if (insn.getOpcode() == RETURN) {
                    // implementation
                    InsnList instList = new InsnList();
                    instList.add(new VarInsnNode(ALOAD, 0));
                    instList.add(new VarInsnNode(ALOAD, 0));
                    instList.add(new VarInsnNode(ILOAD, 1));
                    instList.add(new VarInsnNode(ILOAD, 2));
                    instList.add(new MethodInsnNode(INVOKEVIRTUAL,
                            internal$GuiGameOver,
                            "BetterPlayerRespawn$hook$drawScreen",
                            "(" + Utils.ASM$asObjectDesc(internal$GuiGameOver) + "II)V",
                            false));

                    // insert
                    visitor.instructions.insertBefore(insn, instList);

                    return true;
                }
            }

            return false;
        });

        private final Function<MethodNode, Boolean> visitor;

        PatchingMethod(Function<MethodNode, Boolean> visitor) {
            this.visitor = visitor;
        }

        int patchMethod(MethodNode methodVisitor) {
            // Logger, uncomment and point this to the IFMLLoadingPlugin implemented class
//            RetakePlugin.LOGGER.debug("Visiting method {}{}", methodVisitor.name, methodVisitor.desc);
            boolean result = visitor.apply(methodVisitor);
            // Logger, uncomment and point this to the IFMLLoadingPlugin implemented class
//            RetakePlugin.LOGGER.debug(result ? "Successful visiting method {}{}" : "Failed visiting method {}{}", methodVisitor.name, methodVisitor.desc);
            return result ? 1 : 0;
        }
    }

    public interface Hook {

        static final int homeSpawnBtnID = 90;
        static final int tempSpawnBtnID = 91;

        default void BetterPlayerRespawn$hook$initGui(GuiGameOver gui) {
            gui.buttonList.forEach(button -> button.y += 24);

            gui.buttonList.add(new GuiButton(homeSpawnBtnID, gui.width / 2 - 100, gui.height / 4 + 72, 100, 20, I18n.format("betterplayerrespawn.button.home_spawn")));
            gui.buttonList.add(new GuiButton(tempSpawnBtnID, gui.width / 2 - 100, gui.height / 4 + 72, 100, 20,I18n.format("betterplayerrespawn.button.temp_spawn")));
        }

        default void BetterPlayerRespawn$hook$actionPerformed(GuiGameOver gui, GuiButton guiButton) {
            if (guiButton.id == homeSpawnBtnID) {
                // sent packet to trigger home spawn
//                Retake.NetworkHandler.INSTANCE.sendToServer(new RetakeMessage());
                gui.mc.displayGuiScreen(null);
            } else if (guiButton.id == tempSpawnBtnID) {
                // sent packet to trigger temporary spawn
//                Retake.NetworkHandler.INSTANCE.sendToServer(new RetakeMessage());
                gui.mc.displayGuiScreen(null);
            }
        }

        default void BetterPlayerRespawn$hook$drawScreen(GuiGameOver gui, int mouseX, int mouseY) {
//            if (gui.enableButtonsTimer >= 20) {
//                gui.buttonList.stream().filter(button -> button instanceof RetakeButton && button.isMouseOver() && !button.enabled)
//                        .findAny()
//                        .ifPresent((button) -> gui.handleComponentHover(new RetakeTextComponentTranslation("retake.oncooldown"), mouseX, mouseY));
//            }
        }

    }

    public static class Utils {
        /**
         * Check if checkList contains value or not
         * @param value value to check
         * @param checkList check list
         * @return true if contains, else false
         */
        public static boolean String$isAnyEqual(String value, String... checkList) {
            for (String toCheck : checkList) {
                if (toCheck.equals(value)) return true;
            }

            return false;
        }

        /**
         * Type to field desc
         * @return desc version
         */
        public static String ASM$asObjectDesc(String... multiple) {
            StringBuilder builder = new StringBuilder();
            for (String type : multiple) {
                builder.append(ASM$asObjectDesc(type));
            }
            return builder.toString();
        }

        /**
         * Type to field desc
         * @return desc version
         */
        public static String ASM$asObjectDesc(String type) {
            return "L" + type + ";";
        }
    }

}
