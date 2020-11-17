package FilterTheSpire.simulators;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Collections;

public class RelicRngSimulator {
    public ArrayList<String> commonRelicPool = new ArrayList<>();
    public ArrayList<String> uncommonRelicPool = new ArrayList<>();
    public ArrayList<String> rareRelicPool = new ArrayList<>();
    public ArrayList<String> shopRelicPool = new ArrayList<>();
    public ArrayList<String> bossRelicPool = new ArrayList<>();

    private Random relicRng;
    private long seed;

    public RelicRngSimulator(long seed) {
        this.seed = seed;
        relicRng = new Random(seed);
        bossRelicPool = new ArrayList<>();

        generateCommonRelics();
        generateUncommonRelics();
        generateRareRelics();
        generateShopRelics();
        generateBossRelics();
    }

    public String nthCommonRelic(int n) {
        return commonRelicPool.get(n);
    }

    public String nthUncommonRelic(int n) {
        return uncommonRelicPool.get(n);
    }

    public String nthRareRelic(int n) {
        return rareRelicPool.get(n);
    }

    public String nthShopRelic(int n) {
        return shopRelicPool.get(n);
    }

    public String nthBossRelic(int n) {
        return bossRelicPool.get(n);
    }

    private void generateCommonRelics() {
        RelicLibrary.populateRelicPool(
                this.commonRelicPool,
                AbstractRelic.RelicTier.COMMON,
                AbstractDungeon.player.chosenClass
        );
        Collections.shuffle(this.commonRelicPool, new java.util.Random(relicRng.randomLong()));
    }

    private void generateUncommonRelics() {
        RelicLibrary.populateRelicPool(
                this.uncommonRelicPool,
                AbstractRelic.RelicTier.UNCOMMON,
                AbstractDungeon.player.chosenClass
        );
        Collections.shuffle(this.uncommonRelicPool, new java.util.Random(relicRng.randomLong()));
    }

    private void generateRareRelics() {
        RelicLibrary.populateRelicPool(
                this.rareRelicPool,
                AbstractRelic.RelicTier.RARE,
                AbstractDungeon.player.chosenClass
        );
        Collections.shuffle(this.rareRelicPool, new java.util.Random(relicRng.randomLong()));
    }

    private void generateShopRelics() {
        RelicLibrary.populateRelicPool(
                this.shopRelicPool,
                AbstractRelic.RelicTier.SHOP,
                AbstractDungeon.player.chosenClass
        );
        Collections.shuffle(this.shopRelicPool, new java.util.Random(relicRng.randomLong()));
    }

    private void generateBossRelics() {
        RelicLibrary.populateRelicPool(
                this.bossRelicPool,
                AbstractRelic.RelicTier.BOSS,
                AbstractDungeon.player.chosenClass
        );
        Collections.shuffle(this.bossRelicPool, new java.util.Random(relicRng.randomLong()));
    }
}
