package FilterTheSpire.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UnplayableAttackPatches {
    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    String.class,
                    String.class,
                    String.class,
                    int.class,
                    String.class,
                    AbstractCard.CardType.class,
                    AbstractCard.CardColor.class,
                    AbstractCard.CardRarity.class,
                    AbstractCard.CardTarget.class,
                    DamageInfo.DamageType.class
            }
    )
    public static class ConstructorPatch {
        public static void Postfix(AbstractCard card) {
            if (card.type != AbstractCard.CardType.ATTACK) {
                return;
            }
            card.target = AbstractCard.CardTarget.NONE;
            card.cost = -2;
        }
    }

//    @SpirePatch(
//            clz = AbstractCard.class,
//            method = "createCardImage",
//            paramtypez = {}
//    )
    // This might not be needed, but I'm leaving it here just in case.
//  // public static class CreateCardImagePatch {
//        public static void Postfix(AbstractCard __instance) {
//            if (__instance.type != AbstractCard.CardType.ATTACK) {
//                return;
//            }
//            Color bgColor = (Color)ReflectionHacks.getPrivate(__instance, AbstractCard.class, "CURSE_BG_COLOR");
//            Color backColor = (Color)ReflectionHacks.getPrivate(__instance, AbstractCard.class, "CURSE_TYPE_BACK_COLOR");
//            Color frameColor = (Color)ReflectionHacks.getPrivate(__instance, AbstractCard.class, "CURSE_FRAME_COLOR");
//            Color descBoxColor = (Color)ReflectionHacks.getPrivate(__instance, AbstractCard.class, "CURSE_DESC_BOX_COLOR");
//
//            ReflectionHacks.setPrivate(__instance, AbstractCard.class, "bgColor", bgColor.cpy());
//            ReflectionHacks.setPrivate(__instance, AbstractCard.class, "backColor", backColor.cpy());
//            ReflectionHacks.setPrivate(__instance, AbstractCard.class, "frameColor", frameColor.cpy());
//            ReflectionHacks.setPrivate(__instance, AbstractCard.class, "descBoxColor", descBoxColor.cpy());
//        }
//    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderAttackBg",
            paramtypez = {SpriteBatch.class, float.class, float.class}
    )
    // Makes the card frame black, only while rendering.
    // You can't directly set the card color of attacks to curse,
    // because the game won't find any red attacks to find at a shop, and will crash.
    public static class RenderAttackPatch {
        @SpireInsertPatch(
                rloc=0,
                localvars={}
        )
        public static SpireReturn Insert(AbstractCard __instance, SpriteBatch sb, float x, float y) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method method = __instance.getClass().getSuperclass().getDeclaredMethod("renderHelper", new Class[]{
                SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class
            });
            method.setAccessible(true);
            Color renderColor = (Color)ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
            method.invoke(__instance, sb, renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);

            return SpireReturn.Return(null);
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "canUse",
            paramtypez = {
                    AbstractPlayer.class,
                    AbstractMonster.class
            }
    )
    // Make attacks unable to be played, ever.
    public static class CanUsePatch {
        public static boolean Postfix(boolean __result, AbstractCard __instance) {
            if (__instance.type == AbstractCard.CardType.ATTACK) {
                __result = false;
            }

            return __result;
        }
    }
}
