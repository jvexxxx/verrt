/*       */ package me.levansj01;
/*       */ 
/*       */ import java.lang.invoke.ConstantCallSite;
/*       */ import java.lang.invoke.MethodHandle;
/*       */ import java.lang.invoke.MethodHandles;
/*       */ import java.lang.invoke.MethodType;
/*       */ import java.util.Base64;
/*       */ import java.util.Map;
/*       */ import java.util.Queue;
/*       */ import java.util.UUID;
/*       */ import java.util.function.BiConsumer;
/*       */ import java.util.function.Supplier;
/*       */ import me.levansj01.verus.compat.NMSManager;
/*       */ import org.bukkit.Material;
/*       */ import org.bukkit.Warning;
/*       */ import org.bukkit.World;
/*       */ import org.bukkit.entity.Player;
/*       */ import org.bukkit.inventory.ItemStack;
/*       */ import org.bukkit.potion.PotionEffect;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ public class dc
/*       */   implements om, ju
/*       */ {
/*       */   private ca velocityHandler;
/*       */   private gz lastLastLocation;
/*       */   private boolean digging;
/*       */   private long lastTeleport;
/*       */   private is lastBlockPosition;
/*       */   private boolean clientIsOnGround;
/*       */   private int lastKeepAlive;
/*       */   private final lh<Boolean> shouldHaveReceivedPing;
/*       */   private int lastInventoryOutTick;
/*       */   private dc lastAttacked;
/*       */   private int lastNonMoveTicks;
/*       */   private int allowFlightTicks;
/*       */   @Deprecated
/*       */   private final mu<Integer> connectionFrequency;
/*       */   private final String name;
/*       */   private mn effectHandler;
/*       */   private ha spoofBanCheck;
/*       */   private int totalTicks;
/*       */   private is diggingBlock;
/*       */   private is spawnLocation;
/*       */   private int clientAirTicks;
/*       */   private final lh<Integer> maxPingTick2;
/*       */   private boolean spoofBan;
/*       */   private int lastSentTransaction;
/*       */   private static final int TRIM_SIZE = 80;
/*       */   private gz lastVehicleLocation;
/*       */   private final Queue<Runnable> nextTrans;
/*       */   private final Map<Short, df> transactionMap;
/*       */   private boolean ready;
/*       */   private hv abilityHandler;
/*       */   private final lh<Integer> jumpLevel;
/*       */   @Deprecated
/*       */   private final Queue<BiConsumer<Integer, Double>> pingQueue;
/*       */   private final lh<Integer> speedLevel;
/*       */   private long lastDelayed;
/*       */   private boolean receivedTransaction;
/*       */   private int horizontalSpeedTicks;
/*       */   private nq lastLastLocation2;
/*       */   private df transaction;
/*       */   private final gz location;
/*       */   private int lastAttackTicks;
/*       */   public static final int dqw;
/*       */   private int receivedTransactions;
/*       */   private int averageTransactionPing;
/*       */   private int nonMoveTicks;
/*       */   private int ticks;
/*       */   private int suffocationTicks;
/*       */   private String brand;
/*       */   public static byte[] ux;
/*       */   private boolean alerts;
/*       */   private boolean aimed;
/*       */   private final Queue<Runnable> end;
/*       */   private boolean stoppedDigging;
/*       */   private int survivalTicks;
/*       */   private hx tracker;
/*       */   private boolean moved;
/*       */   private final lh<Integer> pingTicks;
/*       */   private final lh<Integer> maxPingTicks;
/*       */   private final fc checkData;
/*       */   private int verticalVelocityTicks;
/*       */   private int lastTeleportTicks;
/*       */   private gk metadataHandler;
/*       */   private final Queue<nv> reachData;
/*       */   static final int[] $SwitchMap$me$levansj01$verus$compat$api$wrapper$EnumHand;
/*       */   private int horizontalVelocityTicks;
/*       */   @Deprecated
/*       */   private final Queue<gz> teleportList;
/*       */   private nv reachBase;
/*       */   private Boolean sprinting;
/*       */   @Deprecated
/*       */   private final Queue<kx> velocityQueue;
/*       */   private gz lastLocation;
/*       */   private boolean vehicle;
/*       */   public static final boolean diwdjow = true;
/*       */   private long lastFlying;
/*       */   private int lastPing;
/*       */   private p diggingBlockFace;
/*       */   
/*       */   public int getLastInventoryTick() {
/*   106 */     return this.lastInventoryTick;
/*       */   }
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   private bi lastTeleport2;
/*       */   
/*       */   private int blockTicks;
/*       */   
/*       */   private int spawned;
/*       */   
/*       */   private f world;
/*       */   
/*       */   private Boolean sneaking;
/*       */   
/*       */   private int transactionPing;
/*       */   
/*       */   private boolean sentTransaction;
/*       */   
/*       */   private int velocityTicks;
/*       */   
/*       */   private double lastVelZ;
/*       */   
/*       */   private boolean abortedDigging;
/*       */   
/*       */   private long lastRespawn;
/*       */   
/*       */   private int lastFace;
/*       */   
/*       */   private long lastKeepAliveTimestamp;
/*       */   
/*       */   private String focusSubType;
/*       */   
/*       */   private int teleportTicks;
/*       */   
/*       */   private final Map<Long, Long> keepAliveMap;
/*       */   
/*       */   private kb version;
/*       */   
/*       */   private long nanos;
/*       */   
/*       */   private int averagePing;
/*       */   
/*       */   private final lh<PotionEffect[]> effects;
/*       */   
/*       */   private j focus;
/*       */   
/*       */   private final Queue<em> spoofedAlerts;
/*       */   
/*       */   private double lastVelY;
/*       */   
/*       */   private int flyingTicks;
/*       */   private int lastFakeEntityDamageTicks;
/*       */   private final gz vehicleLocation;
/*       */   private long lastLastFlying;
/*       */   private boolean blocking;
/*       */   private final UUID uuid;
/*       */   private final Map<Integer, ka<eb>> recentMoveMap;
/*       */   private int vehicleId;
/*       */   private boolean placing;
/*       */   public static final boolean dqx = false;
/*       */   private boolean swingDigging;
/*       */   private double lastVelX;
/*       */   private boolean resetVelocity;
/*       */   public static String[] uw;
/*       */   private df nextTransaction;
/*       */   private df lastTransaction;
/*       */   private int lastInventoryTick;
/*       */   @Deprecated
/*       */   private final mu<bi> recentTeleports;
/*       */   private int lastSetSlot;
/*       */   private final Player player;
/*       */   private int ping;
/*       */   private jn teleportHandler;
/*       */   private final nc clientData;
/*       */   private long lastClientTransaction;
/*       */   private nq lastLocation2;
/*       */   private static final String[] cjv;
/*       */   private long timestamp;
/*       */   private int fallDamage;
/*       */   static final int[] $SwitchMap$me$levansj01$verus$compat$packets$VPacketPlayInEntityAction$PlayerAction;
/*       */   private boolean banned;
/*       */   private int lastTransactionPing;
/*       */   private cz attributeHandler;
/*       */   private boolean checkSpoofing;
/*       */   private final mj<le> velocityData;
/*       */   private final Queue<Runnable> start;
/*       */   private boolean inventoryOpen;
/*       */   private boolean wasVehicle;
/*       */   private boolean enabled;
/*       */   private boolean debug;
/*       */   @Deprecated
/*       */   private final Queue<bi> teleports;
/*       */   static final int[] $SwitchMap$me$levansj01$verus$compat$packets$VPacketPlayInEntityAction$PlayerAction$Type;
/*       */   private double velY;
/*       */   private boolean fallFlying;
/*       */   private final jz tickerMap;
/*       */   private final lh<Integer> slowLevel;
/*       */   private nq currentLocation2;
/*       */   private long lastFast;
/*       */   private static final String[] cjw;
/*       */   private short lastTransactionID;
/*       */   private int lastAttackedId;
/*       */   
/*       */   public int getLastTransactionPing() {
/*   211 */     return this.lastTransactionPing;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(gn<?> brvr) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tracker : Lme/levansj01/hx;
/*       */     //   4: ifnull -> 17
/*       */     //   7: aload_0
/*       */     //   8: getfield tracker : Lme/levansj01/hx;
/*       */     //   11: aload_1
/*       */     //   12: <illegal opcode> bbmq : (Ljava/lang/Object;Lme/levansj01/gn;)V
/*       */     //   17: aload_0
/*       */     //   18: getfield recentMoveMap : Ljava/util/Map;
/*       */     //   21: aload_1
/*       */     //   22: <illegal opcode> bbmr : (Ljava/lang/Object;)I
/*       */     //   27: <illegal opcode> bbms : (I)Ljava/lang/Integer;
/*       */     //   32: <illegal opcode> bbmt : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   37: checkcast me/levansj01/ka
/*       */     //   40: dup
/*       */     //   41: astore_2
/*       */     //   42: ifnull -> 92
/*       */     //   45: aload_2
/*       */     //   46: <illegal opcode> bbmu : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   51: checkcast me/levansj01/eb
/*       */     //   54: astore_3
/*       */     //   55: <illegal opcode> bbmv : ()J
/*       */     //   60: lstore #4
/*       */     //   62: aload_3
/*       */     //   63: ifnull -> 74
/*       */     //   66: aload_3
/*       */     //   67: lload #4
/*       */     //   69: <illegal opcode> bbmw : (Ljava/lang/Object;J)V
/*       */     //   74: aload_1
/*       */     //   75: lload #4
/*       */     //   77: <illegal opcode> bbmx : (Ljava/lang/Object;J)Lme/levansj01/eb;
/*       */     //   82: astore #6
/*       */     //   84: aload_2
/*       */     //   85: aload #6
/*       */     //   87: <illegal opcode> bbmy : (Ljava/lang/Object;Ljava/lang/Object;)V
/*       */     //   92: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #213	-> 0
/*       */     //   #49052	-> 7
/*       */     //   #1992	-> 17
/*       */     //   #45814	-> 45
/*       */     //   #2677	-> 55
/*       */     //   #4526	-> 62
/*       */     //   #56800	-> 66
/*       */     //   #37664	-> 74
/*       */     //   #60700	-> 84
/*       */     //   #31542	-> 92
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   55	37	3	brvn	Lme/levansj01/eb;
/*       */     //   62	30	4	brvo	J
/*       */     //   84	8	6	brvp	Lme/levansj01/eb;
/*       */     //   0	93	0	brvq	Lme/levansj01/dc;
/*       */     //   0	93	1	brvr	Lme/levansj01/gn;
/*       */     //   42	51	2	brvs	Lme/levansj01/ka;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	93	1	brvr	Lme/levansj01/gn<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getNonMoveTicks() {
/*   534 */     return this.nonMoveTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handleTransaction() {
/*       */     // Byte code:
/*       */     //   0: sipush #29951
/*       */     //   3: istore #5
/*       */     //   5: iload #5
/*       */     //   7: ldc2_w 5932602029196211455
/*       */     //   10: l2i
/*       */     //   11: istore #7
/*       */     //   13: istore #6
/*       */     //   15: iload #6
/*       */     //   17: iload #7
/*       */     //   19: ior
/*       */     //   20: iload #6
/*       */     //   22: iload #7
/*       */     //   24: iand
/*       */     //   25: isub
/*       */     //   26: istore_1
/*       */     //   27: aload_0
/*       */     //   28: aload_0
/*       */     //   29: getfield lastTransactionID : S
/*       */     //   32: sipush #7814
/*       */     //   35: istore #5
/*       */     //   37: iload #5
/*       */     //   39: ldc2_w -1596201600303489401
/*       */     //   42: l2i
/*       */     //   43: istore #7
/*       */     //   45: istore #6
/*       */     //   47: iload #6
/*       */     //   49: iload #7
/*       */     //   51: ior
/*       */     //   52: iload #6
/*       */     //   54: iconst_m1
/*       */     //   55: ixor
/*       */     //   56: iload #7
/*       */     //   58: iconst_m1
/*       */     //   59: ixor
/*       */     //   60: ior
/*       */     //   61: iand
/*       */     //   62: istore #7
/*       */     //   64: istore #6
/*       */     //   66: iload #6
/*       */     //   68: iload #7
/*       */     //   70: ixor
/*       */     //   71: iconst_2
/*       */     //   72: iload #6
/*       */     //   74: iload #7
/*       */     //   76: iand
/*       */     //   77: imul
/*       */     //   78: iadd
/*       */     //   79: i2s
/*       */     //   80: putfield lastTransactionID : S
/*       */     //   83: aload_0
/*       */     //   84: getfield transactionMap : Ljava/util/Map;
/*       */     //   87: aload_0
/*       */     //   88: getfield lastTransactionID : S
/*       */     //   91: <illegal opcode> bbtd : (S)Ljava/lang/Short;
/*       */     //   96: <illegal opcode> bbte : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   101: ifne -> 107
/*       */     //   104: goto -> 146
/*       */     //   107: iload_1
/*       */     //   108: iinc #1, 1
/*       */     //   111: sipush #6910
/*       */     //   114: istore #5
/*       */     //   116: iload #5
/*       */     //   118: sipush #6922
/*       */     //   121: istore #7
/*       */     //   123: istore #6
/*       */     //   125: iload #6
/*       */     //   127: iload #7
/*       */     //   129: iconst_m1
/*       */     //   130: ixor
/*       */     //   131: iand
/*       */     //   132: iload #6
/*       */     //   134: iconst_m1
/*       */     //   135: ixor
/*       */     //   136: iload #7
/*       */     //   138: iand
/*       */     //   139: ior
/*       */     //   140: if_icmpge -> 146
/*       */     //   143: goto -> 27
/*       */     //   146: <illegal opcode> bbtf : ()Lme/levansj01/verus/compat/NMSManager;
/*       */     //   151: aload_0
/*       */     //   152: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   155: aload_0
/*       */     //   156: getfield lastTransactionID : S
/*       */     //   159: <illegal opcode> bbtg : (Ljava/lang/Object;Lorg/bukkit/entity/Player;S)V
/*       */     //   164: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #916	-> 0
/*       */     //   #53085	-> 27
/*       */     //   #54988	-> 83
/*       */     //   #60367	-> 146
/*       */     //   #56722	-> 164
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	165	0	brzk	Lme/levansj01/dc;
/*       */     //   27	138	1	brzl	I
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isHooked() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   4: getstatic me/levansj01/ij.HOOKED : Lme/levansj01/ij;
/*       */     //   7: <illegal opcode> bbwg : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   12: aload_0
/*       */     //   13: <illegal opcode> bbwh : (Ljava/lang/Object;)I
/*       */     //   18: bipush #91
/*       */     //   20: istore #4
/*       */     //   22: ldc2_w -5741946588385771431
/*       */     //   25: l2i
/*       */     //   26: iload #4
/*       */     //   28: istore #6
/*       */     //   30: istore #5
/*       */     //   32: iload #5
/*       */     //   34: iload #6
/*       */     //   36: ior
/*       */     //   37: iload #5
/*       */     //   39: iload #6
/*       */     //   41: iand
/*       */     //   42: isub
/*       */     //   43: istore #6
/*       */     //   45: istore #5
/*       */     //   47: iconst_2
/*       */     //   48: iload #5
/*       */     //   50: iload #6
/*       */     //   52: ior
/*       */     //   53: imul
/*       */     //   54: iload #5
/*       */     //   56: iload #6
/*       */     //   58: ixor
/*       */     //   59: isub
/*       */     //   60: sipush #7136
/*       */     //   63: istore #4
/*       */     //   65: iload #4
/*       */     //   67: sipush #7139
/*       */     //   70: istore #6
/*       */     //   72: istore #5
/*       */     //   74: iload #5
/*       */     //   76: iload #6
/*       */     //   78: ior
/*       */     //   79: iload #5
/*       */     //   81: iload #6
/*       */     //   83: iand
/*       */     //   84: isub
/*       */     //   85: imul
/*       */     //   86: if_icmple -> 92
/*       */     //   89: goto -> 123
/*       */     //   92: sipush #32030
/*       */     //   95: istore #4
/*       */     //   97: ldc2_w 1002341553231920415
/*       */     //   100: l2i
/*       */     //   101: iload #4
/*       */     //   103: istore #6
/*       */     //   105: istore #5
/*       */     //   107: iload #5
/*       */     //   109: iload #6
/*       */     //   111: ior
/*       */     //   112: iload #5
/*       */     //   114: iload #6
/*       */     //   116: iand
/*       */     //   117: iconst_m1
/*       */     //   118: ixor
/*       */     //   119: iand
/*       */     //   120: goto -> 145
/*       */     //   123: ldc2_w -5855906235550777999
/*       */     //   126: l2i
/*       */     //   127: sipush #15729
/*       */     //   130: istore #6
/*       */     //   132: istore #5
/*       */     //   134: iload #5
/*       */     //   136: iload #6
/*       */     //   138: ior
/*       */     //   139: iload #5
/*       */     //   141: iload #6
/*       */     //   143: iand
/*       */     //   144: isub
/*       */     //   145: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #2489	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	146	0	bsbq	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public lh<Integer> getMaxPingTick2() {
/*  2739 */     return this.maxPingTick2;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean hadJumpBoost() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   4: getstatic me/levansj01/ij.JUMP_BOOST : Lme/levansj01/ij;
/*       */     //   7: <illegal opcode> bbvf : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   12: istore_1
/*       */     //   13: iload_1
/*       */     //   14: ifgt -> 20
/*       */     //   17: goto -> 137
/*       */     //   20: iload_1
/*       */     //   21: aload_0
/*       */     //   22: <illegal opcode> bbvg : (Ljava/lang/Object;)I
/*       */     //   27: sipush #4196
/*       */     //   30: istore #5
/*       */     //   32: ldc2_w 7660660607250600038
/*       */     //   35: l2i
/*       */     //   36: iload #5
/*       */     //   38: istore #7
/*       */     //   40: istore #6
/*       */     //   42: iload #6
/*       */     //   44: iload #7
/*       */     //   46: ior
/*       */     //   47: iload #6
/*       */     //   49: iload #7
/*       */     //   51: iand
/*       */     //   52: iconst_m1
/*       */     //   53: ixor
/*       */     //   54: iand
/*       */     //   55: istore #7
/*       */     //   57: istore #6
/*       */     //   59: iload #6
/*       */     //   61: iload #7
/*       */     //   63: ixor
/*       */     //   64: iconst_2
/*       */     //   65: iload #6
/*       */     //   67: iload #7
/*       */     //   69: iand
/*       */     //   70: imul
/*       */     //   71: iadd
/*       */     //   72: sipush #30908
/*       */     //   75: istore #5
/*       */     //   77: iload #5
/*       */     //   79: ldc2_w 5389790271187744958
/*       */     //   82: l2i
/*       */     //   83: istore #7
/*       */     //   85: istore #6
/*       */     //   87: iload #6
/*       */     //   89: iload #7
/*       */     //   91: ior
/*       */     //   92: iload #6
/*       */     //   94: iload #7
/*       */     //   96: iand
/*       */     //   97: iconst_m1
/*       */     //   98: ixor
/*       */     //   99: iand
/*       */     //   100: imul
/*       */     //   101: if_icmple -> 107
/*       */     //   104: goto -> 137
/*       */     //   107: sipush #19912
/*       */     //   110: istore #5
/*       */     //   112: iload #5
/*       */     //   114: sipush #19913
/*       */     //   117: istore #7
/*       */     //   119: istore #6
/*       */     //   121: iload #6
/*       */     //   123: iload #7
/*       */     //   125: ior
/*       */     //   126: iload #6
/*       */     //   128: iload #7
/*       */     //   130: iand
/*       */     //   131: iconst_m1
/*       */     //   132: ixor
/*       */     //   133: iand
/*       */     //   134: goto -> 161
/*       */     //   137: ldc2_w -4935418748866631538
/*       */     //   140: l2i
/*       */     //   141: sipush #26766
/*       */     //   144: istore #7
/*       */     //   146: istore #6
/*       */     //   148: iload #6
/*       */     //   150: iload #7
/*       */     //   152: ior
/*       */     //   153: iload #6
/*       */     //   155: iload #7
/*       */     //   157: iand
/*       */     //   158: iconst_m1
/*       */     //   159: ixor
/*       */     //   160: iand
/*       */     //   161: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #3467	-> 0
/*       */     //   #41205	-> 13
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   13	149	1	bsay	I
/*       */     //   0	162	0	bsax	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isSpoofBan() {
/*  4208 */     return this.spoofBan;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(hu<?> brxa) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield world : Lme/levansj01/f;
/*       */     //   4: ifnull -> 17
/*       */     //   7: aload_0
/*       */     //   8: getfield world : Lme/levansj01/f;
/*       */     //   11: aload_1
/*       */     //   12: <illegal opcode> bboo : (Ljava/lang/Object;Lme/levansj01/hu;)V
/*       */     //   17: aload_0
/*       */     //   18: getfield abilityHandler : Lme/levansj01/hv;
/*       */     //   21: ifnull -> 34
/*       */     //   24: aload_0
/*       */     //   25: getfield abilityHandler : Lme/levansj01/hv;
/*       */     //   28: aload_1
/*       */     //   29: <illegal opcode> bbop : (Ljava/lang/Object;Lme/levansj01/hu;)V
/*       */     //   34: aload_0
/*       */     //   35: sipush #1066
/*       */     //   38: istore #5
/*       */     //   40: iload #5
/*       */     //   42: sipush #1066
/*       */     //   45: istore #7
/*       */     //   47: istore #6
/*       */     //   49: iload #6
/*       */     //   51: iload #7
/*       */     //   53: ior
/*       */     //   54: iload #6
/*       */     //   56: iload #7
/*       */     //   58: iand
/*       */     //   59: iconst_m1
/*       */     //   60: ixor
/*       */     //   61: iand
/*       */     //   62: putfield inventoryOpen : Z
/*       */     //   65: aload_0
/*       */     //   66: sipush #2134
/*       */     //   69: istore #5
/*       */     //   71: iload #5
/*       */     //   73: sipush #2134
/*       */     //   76: istore #7
/*       */     //   78: istore #6
/*       */     //   80: iload #6
/*       */     //   82: iload #7
/*       */     //   84: iconst_m1
/*       */     //   85: ixor
/*       */     //   86: iand
/*       */     //   87: iload #6
/*       */     //   89: iconst_m1
/*       */     //   90: ixor
/*       */     //   91: iload #7
/*       */     //   93: iand
/*       */     //   94: ior
/*       */     //   95: putfield blocking : Z
/*       */     //   98: aload_0
/*       */     //   99: <illegal opcode> bboq : ()J
/*       */     //   104: putfield lastRespawn : J
/*       */     //   107: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #4429	-> 0
/*       */     //   #37306	-> 7
/*       */     //   #49862	-> 17
/*       */     //   #55888	-> 24
/*       */     //   #31581	-> 34
/*       */     //   #53163	-> 65
/*       */     //   #18936	-> 98
/*       */     //   #48278	-> 107
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	108	1	brxa	Lme/levansj01/hu;
/*       */     //   0	108	0	brwz	Lme/levansj01/dc;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	108	1	brxa	Lme/levansj01/hu<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isGliding() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   4: getstatic me/levansj01/ij.GLIDING : Lme/levansj01/ij;
/*       */     //   7: <illegal opcode> bbvx : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   12: aload_0
/*       */     //   13: <illegal opcode> bbvy : (Ljava/lang/Object;)I
/*       */     //   18: sipush #18521
/*       */     //   21: ldc2_w -2459855137674344357
/*       */     //   24: l2i
/*       */     //   25: istore #6
/*       */     //   27: istore #5
/*       */     //   29: iload #5
/*       */     //   31: iload #6
/*       */     //   33: ior
/*       */     //   34: iload #5
/*       */     //   36: iload #6
/*       */     //   38: iand
/*       */     //   39: iconst_m1
/*       */     //   40: ixor
/*       */     //   41: iand
/*       */     //   42: istore #6
/*       */     //   44: istore #5
/*       */     //   46: iload #5
/*       */     //   48: iload #6
/*       */     //   50: ixor
/*       */     //   51: iconst_2
/*       */     //   52: iload #5
/*       */     //   54: iload #6
/*       */     //   56: iand
/*       */     //   57: imul
/*       */     //   58: iadd
/*       */     //   59: sipush #25181
/*       */     //   62: istore #4
/*       */     //   64: iload #4
/*       */     //   66: ldc2_w -4319886047711305128
/*       */     //   69: l2i
/*       */     //   70: istore #6
/*       */     //   72: istore #5
/*       */     //   74: iload #5
/*       */     //   76: iload #6
/*       */     //   78: ior
/*       */     //   79: iload #5
/*       */     //   81: iload #6
/*       */     //   83: iand
/*       */     //   84: iconst_m1
/*       */     //   85: ixor
/*       */     //   86: iand
/*       */     //   87: imul
/*       */     //   88: if_icmpgt -> 94
/*       */     //   91: goto -> 131
/*       */     //   94: aload_0
/*       */     //   95: getfield metadataHandler : Lme/levansj01/gk;
/*       */     //   98: ifnull -> 164
/*       */     //   101: aload_0
/*       */     //   102: getfield metadataHandler : Lme/levansj01/gk;
/*       */     //   105: <illegal opcode> bbvz : (Ljava/lang/Object;)Z
/*       */     //   110: ifeq -> 116
/*       */     //   113: goto -> 131
/*       */     //   116: aload_0
/*       */     //   117: getfield metadataHandler : Lme/levansj01/gk;
/*       */     //   120: <illegal opcode> bbwa : (Ljava/lang/Object;)Z
/*       */     //   125: ifne -> 131
/*       */     //   128: goto -> 164
/*       */     //   131: sipush #28178
/*       */     //   134: istore #4
/*       */     //   136: ldc2_w 461212228402572819
/*       */     //   139: l2i
/*       */     //   140: iload #4
/*       */     //   142: istore #6
/*       */     //   144: istore #5
/*       */     //   146: iload #5
/*       */     //   148: iload #6
/*       */     //   150: ior
/*       */     //   151: iload #5
/*       */     //   153: iconst_m1
/*       */     //   154: ixor
/*       */     //   155: iload #6
/*       */     //   157: iconst_m1
/*       */     //   158: ixor
/*       */     //   159: ior
/*       */     //   160: iand
/*       */     //   161: goto -> 191
/*       */     //   164: sipush #21595
/*       */     //   167: istore #4
/*       */     //   169: iload #4
/*       */     //   171: sipush #21595
/*       */     //   174: istore #6
/*       */     //   176: istore #5
/*       */     //   178: iload #5
/*       */     //   180: iload #6
/*       */     //   182: ior
/*       */     //   183: iload #5
/*       */     //   185: iload #6
/*       */     //   187: iand
/*       */     //   188: iconst_m1
/*       */     //   189: ixor
/*       */     //   190: iand
/*       */     //   191: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #4482	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	192	0	bsbm	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(y<?> brxe) {
/*       */     // Byte code:
/*       */     //   0: <illegal opcode> bbor : ()J
/*       */     //   5: lstore_2
/*       */     //   6: aload_1
/*       */     //   7: <illegal opcode> bbos : (Ljava/lang/Object;)Lme/levansj01/is;
/*       */     //   12: astore #4
/*       */     //   14: new me/levansj01/gz
/*       */     //   17: dup
/*       */     //   18: lload_2
/*       */     //   19: aload_0
/*       */     //   20: getfield totalTicks : I
/*       */     //   23: aload #4
/*       */     //   25: <illegal opcode> bbot : (Ljava/lang/Object;)I
/*       */     //   30: i2d
/*       */     //   31: aload #4
/*       */     //   33: <illegal opcode> bbou : (Ljava/lang/Object;)I
/*       */     //   38: i2d
/*       */     //   39: aload #4
/*       */     //   41: <illegal opcode> bbov : (Ljava/lang/Object;)I
/*       */     //   46: i2d
/*       */     //   47: fconst_0
/*       */     //   48: fconst_0
/*       */     //   49: aconst_null
/*       */     //   50: ldc2_w 8930632868460242144
/*       */     //   53: l2i
/*       */     //   54: sipush #9440
/*       */     //   57: istore #7
/*       */     //   59: istore #6
/*       */     //   61: iload #6
/*       */     //   63: iload #7
/*       */     //   65: ior
/*       */     //   66: iload #6
/*       */     //   68: iload #7
/*       */     //   70: iand
/*       */     //   71: isub
/*       */     //   72: invokespecial <init> : (JIDDDFFLjava/lang/Boolean;Z)V
/*       */     //   75: astore #5
/*       */     //   77: aload_0
/*       */     //   78: getfield teleportList : Ljava/util/Queue;
/*       */     //   81: aload #5
/*       */     //   83: <illegal opcode> bbow : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   88: pop
/*       */     //   89: aload_0
/*       */     //   90: ldc2_w -2865250684062645515
/*       */     //   93: l2i
/*       */     //   94: sipush #17141
/*       */     //   97: istore #7
/*       */     //   99: istore #6
/*       */     //   101: iload #6
/*       */     //   103: iload #7
/*       */     //   105: ior
/*       */     //   106: iload #6
/*       */     //   108: iload #7
/*       */     //   110: iand
/*       */     //   111: iconst_m1
/*       */     //   112: ixor
/*       */     //   113: iand
/*       */     //   114: putfield teleportTicks : I
/*       */     //   117: aload_0
/*       */     //   118: <illegal opcode> bbox : ()J
/*       */     //   123: putfield lastTeleport : J
/*       */     //   126: aload_0
/*       */     //   127: dconst_0
/*       */     //   128: putfield velY : D
/*       */     //   131: aload_0
/*       */     //   132: dconst_0
/*       */     //   133: putfield lastVelZ : D
/*       */     //   136: aload_0
/*       */     //   137: dconst_0
/*       */     //   138: putfield lastVelY : D
/*       */     //   141: aload_0
/*       */     //   142: dconst_0
/*       */     //   143: putfield lastVelX : D
/*       */     //   146: aload_0
/*       */     //   147: aload_1
/*       */     //   148: <illegal opcode> bboy : (Ljava/lang/Object;)Lme/levansj01/is;
/*       */     //   153: putfield spawnLocation : Lme/levansj01/is;
/*       */     //   156: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #5163	-> 0
/*       */     //   #14923	-> 6
/*       */     //   #39310	-> 14
/*       */     //   #8920	-> 77
/*       */     //   #27451	-> 89
/*       */     //   #53199	-> 117
/*       */     //   #25841	-> 126
/*       */     //   #20180	-> 131
/*       */     //   #42821	-> 136
/*       */     //   #30005	-> 141
/*       */     //   #45194	-> 146
/*       */     //   #12227	-> 156
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	157	0	brxd	Lme/levansj01/dc;
/*       */     //   0	157	1	brxe	Lme/levansj01/y;
/*       */     //   6	151	2	brxf	J
/*       */     //   14	143	4	brxg	Lme/levansj01/is;
/*       */     //   77	80	5	brxh	Lme/levansj01/gz;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	157	1	brxe	Lme/levansj01/y<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Map<Short, df> getTransactionMap() {
/*  5274 */     return this.transactionMap;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void nextTrans(Runnable brrr) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield nextTrans : Ljava/util/Queue;
/*       */     //   4: aload_1
/*       */     //   5: <illegal opcode> bazh : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   10: pop
/*       */     //   11: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #5574	-> 0
/*       */     //   #9733	-> 11
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	12	0	brrq	Lme/levansj01/dc;
/*       */     //   0	12	1	brrr	Ljava/lang/Runnable;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getLastAttackedId() {
/*  5735 */     return this.lastAttackedId;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setNanos(long bsir) {
/*  5780 */     this.nanos = bsir;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getSurvivalTicks() {
/*  5847 */     return this.survivalTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void updateVersion() {
/*       */     // Byte code:
/*       */     //   0: <illegal opcode> bayp : ()Lme/levansj01/verus/compat/NMSManager;
/*       */     //   5: aload_0
/*       */     //   6: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   9: <illegal opcode> bayq : (Ljava/lang/Object;Lorg/bukkit/entity/Player;)Lme/levansj01/kb;
/*       */     //   14: astore_1
/*       */     //   15: goto -> 137
/*       */     //   18: astore_2
/*       */     //   19: <illegal opcode> bayr : ()Lme/levansj01/launcher/VerusLauncher;
/*       */     //   24: <illegal opcode> bays : (Ljava/lang/Object;)Ljava/util/logging/Logger;
/*       */     //   29: getstatic java/util/logging/Level.SEVERE : Ljava/util/logging/Level;
/*       */     //   32: new java/lang/StringBuilder
/*       */     //   35: dup
/*       */     //   36: invokespecial <init> : ()V
/*       */     //   39: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   42: sipush #16027
/*       */     //   45: istore #6
/*       */     //   47: ldc2_w 4387826967135665825
/*       */     //   50: l2i
/*       */     //   51: iload #6
/*       */     //   53: istore #8
/*       */     //   55: istore #7
/*       */     //   57: iload #7
/*       */     //   59: iload #8
/*       */     //   61: ior
/*       */     //   62: iload #7
/*       */     //   64: iload #8
/*       */     //   66: iand
/*       */     //   67: isub
/*       */     //   68: aaload
/*       */     //   69: <illegal opcode> bayt : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   74: aload_0
/*       */     //   75: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   78: <illegal opcode> bayu : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   83: <illegal opcode> bayv : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   88: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   91: sipush #2180
/*       */     //   94: sipush #2239
/*       */     //   97: istore #8
/*       */     //   99: istore #7
/*       */     //   101: iload #7
/*       */     //   103: iload #8
/*       */     //   105: iconst_m1
/*       */     //   106: ixor
/*       */     //   107: iand
/*       */     //   108: iload #7
/*       */     //   110: iconst_m1
/*       */     //   111: ixor
/*       */     //   112: iload #8
/*       */     //   114: iand
/*       */     //   115: ior
/*       */     //   116: aaload
/*       */     //   117: <illegal opcode> bayw : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   122: <illegal opcode> bayx : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   127: aload_2
/*       */     //   128: <illegal opcode> bayy : (Ljava/lang/Object;Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
/*       */     //   133: getstatic me/levansj01/kb.VERSION_UNSUPPORTED : Lme/levansj01/kb;
/*       */     //   136: astore_1
/*       */     //   137: aload_0
/*       */     //   138: getfield version : Lme/levansj01/kb;
/*       */     //   141: aload_1
/*       */     //   142: <illegal opcode> bayz : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   147: ifeq -> 153
/*       */     //   150: goto -> 174
/*       */     //   153: aload_0
/*       */     //   154: aload_1
/*       */     //   155: putfield version : Lme/levansj01/kb;
/*       */     //   158: aload_0
/*       */     //   159: getfield checkData : Lme/levansj01/fc;
/*       */     //   162: ifnull -> 174
/*       */     //   165: aload_0
/*       */     //   166: getfield checkData : Lme/levansj01/fc;
/*       */     //   169: <illegal opcode> baza : (Ljava/lang/Object;)V
/*       */     //   174: aload_0
/*       */     //   175: getfield tracker : Lme/levansj01/hx;
/*       */     //   178: ifnull -> 190
/*       */     //   181: aload_0
/*       */     //   182: getfield tracker : Lme/levansj01/hx;
/*       */     //   185: <illegal opcode> bazb : (Ljava/lang/Object;)V
/*       */     //   190: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #5872	-> 0
/*       */     //   #34084	-> 15
/*       */     //   #8157	-> 18
/*       */     //   #5975	-> 19
/*       */     //   #36445	-> 133
/*       */     //   #8701	-> 137
/*       */     //   #33427	-> 153
/*       */     //   #14813	-> 158
/*       */     //   #9478	-> 165
/*       */     //   #48986	-> 174
/*       */     //   #38846	-> 181
/*       */     //   #28635	-> 190
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   15	3	1	brre	Lme/levansj01/kb;
/*       */     //   19	118	2	brrf	Ljava/lang/Throwable;
/*       */     //   0	191	0	brrg	Lme/levansj01/dc;
/*       */     //   137	54	1	brrh	Lme/levansj01/kb;
/*       */     // Exception table:
/*       */     //   from	to	target	type
/*       */     //   0	15	18	java/lang/Throwable
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public long getLastTeleport() {
/*  5892 */     return this.lastTeleport;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public gz getLastLocation() {
/*  6177 */     return this.lastLocation;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public bi getLastTeleport2() {
/*  6280 */     return this.lastTeleport2;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public Integer getLag() {
/*       */     // Byte code:
/*       */     //   0: sipush #17667
/*       */     //   3: istore #4
/*       */     //   5: ldc2_w 1276342762861511939
/*       */     //   8: l2i
/*       */     //   9: iload #4
/*       */     //   11: istore #6
/*       */     //   13: istore #5
/*       */     //   15: iload #5
/*       */     //   17: iload #6
/*       */     //   19: ior
/*       */     //   20: iload #5
/*       */     //   22: iload #6
/*       */     //   24: iand
/*       */     //   25: isub
/*       */     //   26: <illegal opcode> bbtm : (I)Ljava/lang/Integer;
/*       */     //   31: aload_0
/*       */     //   32: getfield connectionFrequency : Lme/levansj01/mu;
/*       */     //   35: <illegal opcode> bbtn : (Ljava/lang/Number;Ljava/lang/Iterable;)D
/*       */     //   40: <illegal opcode> bbto : (D)D
/*       */     //   45: d2i
/*       */     //   46: <illegal opcode> bbtp : (I)Ljava/lang/Integer;
/*       */     //   51: areturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #6504	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	52	0	brzq	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void release() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield world : Lme/levansj01/f;
/*       */     //   4: ifnull -> 16
/*       */     //   7: aload_0
/*       */     //   8: getfield world : Lme/levansj01/f;
/*       */     //   11: <illegal opcode> bbtc : (Ljava/lang/Object;)V
/*       */     //   16: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #6855	-> 0
/*       */     //   #12182	-> 7
/*       */     //   #63704	-> 16
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	17	0	brzj	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(md<?> brup) {
/*       */     // Byte code:
/*       */     //   0: <illegal opcode> bbky : ()Lme/levansj01/verus/compat/NMSManager;
/*       */     //   5: <illegal opcode> bbkz : (Ljava/lang/Object;)Lme/levansj01/ig;
/*       */     //   10: getstatic me/levansj01/ig.v1_11_R1 : Lme/levansj01/ig;
/*       */     //   13: <illegal opcode> bbla : (Ljava/lang/Object;Lme/levansj01/ig;)Z
/*       */     //   18: ifne -> 24
/*       */     //   21: goto -> 157
/*       */     //   24: aload_1
/*       */     //   25: <illegal opcode> bblb : (Ljava/lang/Object;)Ljava/lang/Integer;
/*       */     //   30: <illegal opcode> bblc : (Ljava/lang/Object;)I
/*       */     //   35: ifeq -> 41
/*       */     //   38: goto -> 157
/*       */     //   41: aload_1
/*       */     //   42: <illegal opcode> bbld : (Ljava/lang/Object;)Ljava/lang/Integer;
/*       */     //   47: <illegal opcode> bble : (Ljava/lang/Object;)I
/*       */     //   52: sipush #13495
/*       */     //   55: istore #5
/*       */     //   57: iload #5
/*       */     //   59: sipush #13489
/*       */     //   62: istore #7
/*       */     //   64: istore #6
/*       */     //   66: iload #6
/*       */     //   68: iload #7
/*       */     //   70: iconst_m1
/*       */     //   71: ixor
/*       */     //   72: iand
/*       */     //   73: iload #6
/*       */     //   75: iconst_m1
/*       */     //   76: ixor
/*       */     //   77: iload #7
/*       */     //   79: iand
/*       */     //   80: ior
/*       */     //   81: if_icmpeq -> 87
/*       */     //   84: goto -> 157
/*       */     //   87: aload_1
/*       */     //   88: <illegal opcode> bblf : (Ljava/lang/Object;)Lorg/bukkit/inventory/ItemStack;
/*       */     //   93: ifnull -> 157
/*       */     //   96: aload_1
/*       */     //   97: <illegal opcode> bblg : (Ljava/lang/Object;)Lorg/bukkit/inventory/ItemStack;
/*       */     //   102: <illegal opcode> bblh : (Ljava/lang/Object;)Lorg/bukkit/Material;
/*       */     //   107: getstatic me/levansj01/if.ELYTRA : Lorg/bukkit/Material;
/*       */     //   110: if_acmpeq -> 116
/*       */     //   113: goto -> 157
/*       */     //   116: aload_0
/*       */     //   117: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   120: getstatic me/levansj01/ij.ELYTRA_EXIT : Lme/levansj01/ij;
/*       */     //   123: sipush #-32410
/*       */     //   126: istore #5
/*       */     //   128: iload #5
/*       */     //   130: sipush #32506
/*       */     //   133: istore #7
/*       */     //   135: istore #6
/*       */     //   137: iload #6
/*       */     //   139: iload #7
/*       */     //   141: ior
/*       */     //   142: iload #6
/*       */     //   144: iconst_m1
/*       */     //   145: ixor
/*       */     //   146: iload #7
/*       */     //   148: iconst_m1
/*       */     //   149: ixor
/*       */     //   150: ior
/*       */     //   151: iand
/*       */     //   152: <illegal opcode> bbli : (Ljava/lang/Object;Lme/levansj01/ij;I)V
/*       */     //   157: aload_0
/*       */     //   158: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   161: getstatic me/levansj01/ij.WINDOW_CLICK : Lme/levansj01/ij;
/*       */     //   164: <illegal opcode> bblj : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   169: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #6855	-> 0
/*       */     //   #28670	-> 116
/*       */     //   #28603	-> 157
/*       */     //   #16843	-> 169
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	170	1	brup	Lme/levansj01/md;
/*       */     //   0	170	0	bruo	Lme/levansj01/dc;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	170	1	brup	Lme/levansj01/md<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public nv getReachBase() {
/*  6919 */     return this.reachBase;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static String bfj(String paramString1, String paramString2, byte[] paramArrayOfbyte) {
/*       */     // Byte code:
/*       */     //   0: <illegal opcode> bcei : ()Ljava/util/Base64$Decoder;
/*       */     //   5: aload_0
/*       */     //   6: <illegal opcode> bcej : (Ljava/lang/Object;Ljava/lang/String;)[B
/*       */     //   11: checkcast [B
/*       */     //   14: astore_3
/*       */     //   15: sipush #29653
/*       */     //   18: istore #15
/*       */     //   20: ldc2_w -7304453886784277563
/*       */     //   23: l2i
/*       */     //   24: iload #15
/*       */     //   26: istore #17
/*       */     //   28: istore #16
/*       */     //   30: iload #16
/*       */     //   32: iload #17
/*       */     //   34: ior
/*       */     //   35: iload #16
/*       */     //   37: iload #17
/*       */     //   39: iand
/*       */     //   40: iconst_m1
/*       */     //   41: ixor
/*       */     //   42: iand
/*       */     //   43: newarray byte
/*       */     //   45: astore #4
/*       */     //   47: aload #4
/*       */     //   49: sipush #8874
/*       */     //   52: sipush #8870
/*       */     //   55: istore #17
/*       */     //   57: istore #16
/*       */     //   59: iload #16
/*       */     //   61: iload #17
/*       */     //   63: ior
/*       */     //   64: iload #16
/*       */     //   66: iload #17
/*       */     //   68: iand
/*       */     //   69: isub
/*       */     //   70: ldc2_w -8007287506242355677
/*       */     //   73: l2i
/*       */     //   74: sipush #15899
/*       */     //   77: istore #17
/*       */     //   79: istore #16
/*       */     //   81: iload #16
/*       */     //   83: iload #17
/*       */     //   85: iconst_m1
/*       */     //   86: ixor
/*       */     //   87: iand
/*       */     //   88: iload #16
/*       */     //   90: iconst_m1
/*       */     //   91: ixor
/*       */     //   92: iload #17
/*       */     //   94: iand
/*       */     //   95: ior
/*       */     //   96: bastore
/*       */     //   97: aload #4
/*       */     //   99: sipush #16372
/*       */     //   102: sipush #16382
/*       */     //   105: istore #17
/*       */     //   107: istore #16
/*       */     //   109: iload #16
/*       */     //   111: iload #17
/*       */     //   113: ior
/*       */     //   114: iload #16
/*       */     //   116: iload #17
/*       */     //   118: iand
/*       */     //   119: isub
/*       */     //   120: ldc2_w -8967540029240900897
/*       */     //   123: l2i
/*       */     //   124: sipush #751
/*       */     //   127: istore #17
/*       */     //   129: istore #16
/*       */     //   131: iload #16
/*       */     //   133: iload #17
/*       */     //   135: ior
/*       */     //   136: iload #16
/*       */     //   138: iload #17
/*       */     //   140: iand
/*       */     //   141: isub
/*       */     //   142: bastore
/*       */     //   143: aload #4
/*       */     //   145: ldc2_w -498709341596019607
/*       */     //   148: l2i
/*       */     //   149: sipush #5218
/*       */     //   152: istore #17
/*       */     //   154: istore #16
/*       */     //   156: iload #16
/*       */     //   158: iload #17
/*       */     //   160: iconst_m1
/*       */     //   161: ixor
/*       */     //   162: iand
/*       */     //   163: iload #16
/*       */     //   165: iconst_m1
/*       */     //   166: ixor
/*       */     //   167: iload #17
/*       */     //   169: iand
/*       */     //   170: ior
/*       */     //   171: sipush #-15407
/*       */     //   174: sipush #15450
/*       */     //   177: istore #17
/*       */     //   179: istore #16
/*       */     //   181: iload #16
/*       */     //   183: iload #17
/*       */     //   185: ior
/*       */     //   186: iload #16
/*       */     //   188: iload #17
/*       */     //   190: iand
/*       */     //   191: isub
/*       */     //   192: bastore
/*       */     //   193: aload #4
/*       */     //   195: sipush #2908
/*       */     //   198: ldc2_w 2909250274663074655
/*       */     //   201: l2i
/*       */     //   202: istore #17
/*       */     //   204: istore #16
/*       */     //   206: iload #16
/*       */     //   208: iload #17
/*       */     //   210: ior
/*       */     //   211: iload #16
/*       */     //   213: iconst_m1
/*       */     //   214: ixor
/*       */     //   215: iload #17
/*       */     //   217: iconst_m1
/*       */     //   218: ixor
/*       */     //   219: ior
/*       */     //   220: iand
/*       */     //   221: sipush #730
/*       */     //   224: istore #15
/*       */     //   226: iload #15
/*       */     //   228: sipush #643
/*       */     //   231: istore #17
/*       */     //   233: istore #16
/*       */     //   235: iload #16
/*       */     //   237: iload #17
/*       */     //   239: ior
/*       */     //   240: iload #16
/*       */     //   242: iload #17
/*       */     //   244: iand
/*       */     //   245: iconst_m1
/*       */     //   246: ixor
/*       */     //   247: iand
/*       */     //   248: bastore
/*       */     //   249: aload #4
/*       */     //   251: sipush #26517
/*       */     //   254: istore #15
/*       */     //   256: iload #15
/*       */     //   258: ldc2_w -1861891940841134185
/*       */     //   261: l2i
/*       */     //   262: istore #17
/*       */     //   264: istore #16
/*       */     //   266: iload #16
/*       */     //   268: iload #17
/*       */     //   270: ior
/*       */     //   271: iload #16
/*       */     //   273: iload #17
/*       */     //   275: iand
/*       */     //   276: isub
/*       */     //   277: sipush #-2664
/*       */     //   280: istore #15
/*       */     //   282: ldc2_w -2103214930453853659
/*       */     //   285: l2i
/*       */     //   286: iload #15
/*       */     //   288: istore #17
/*       */     //   290: istore #16
/*       */     //   292: iload #16
/*       */     //   294: iload #17
/*       */     //   296: ior
/*       */     //   297: iload #16
/*       */     //   299: iconst_m1
/*       */     //   300: ixor
/*       */     //   301: iload #17
/*       */     //   303: iconst_m1
/*       */     //   304: ixor
/*       */     //   305: ior
/*       */     //   306: iand
/*       */     //   307: bastore
/*       */     //   308: aload #4
/*       */     //   310: sipush #26461
/*       */     //   313: sipush #26461
/*       */     //   316: istore #17
/*       */     //   318: istore #16
/*       */     //   320: iload #16
/*       */     //   322: iload #17
/*       */     //   324: ior
/*       */     //   325: iload #16
/*       */     //   327: iconst_m1
/*       */     //   328: ixor
/*       */     //   329: iload #17
/*       */     //   331: iconst_m1
/*       */     //   332: ixor
/*       */     //   333: ior
/*       */     //   334: iand
/*       */     //   335: ldc2_w 9143873927372634378
/*       */     //   338: l2i
/*       */     //   339: sipush #-31036
/*       */     //   342: istore #17
/*       */     //   344: istore #16
/*       */     //   346: iload #16
/*       */     //   348: iload #17
/*       */     //   350: ior
/*       */     //   351: iload #16
/*       */     //   353: iconst_m1
/*       */     //   354: ixor
/*       */     //   355: iload #17
/*       */     //   357: iconst_m1
/*       */     //   358: ixor
/*       */     //   359: ior
/*       */     //   360: iand
/*       */     //   361: bastore
/*       */     //   362: aload #4
/*       */     //   364: sipush #29044
/*       */     //   367: istore #15
/*       */     //   369: iload #15
/*       */     //   371: sipush #29051
/*       */     //   374: istore #17
/*       */     //   376: istore #16
/*       */     //   378: iload #16
/*       */     //   380: iload #17
/*       */     //   382: ior
/*       */     //   383: iload #16
/*       */     //   385: iload #17
/*       */     //   387: iand
/*       */     //   388: isub
/*       */     //   389: sipush #-16656
/*       */     //   392: istore #15
/*       */     //   394: iload #15
/*       */     //   396: sipush #16705
/*       */     //   399: istore #17
/*       */     //   401: istore #16
/*       */     //   403: iload #16
/*       */     //   405: iload #17
/*       */     //   407: ior
/*       */     //   408: iload #16
/*       */     //   410: iload #17
/*       */     //   412: iand
/*       */     //   413: isub
/*       */     //   414: bastore
/*       */     //   415: aload #4
/*       */     //   417: sipush #21101
/*       */     //   420: istore #15
/*       */     //   422: iload #15
/*       */     //   424: ldc2_w -6389706890179227028
/*       */     //   427: l2i
/*       */     //   428: istore #17
/*       */     //   430: istore #16
/*       */     //   432: iload #16
/*       */     //   434: iload #17
/*       */     //   436: iconst_m1
/*       */     //   437: ixor
/*       */     //   438: iand
/*       */     //   439: iload #16
/*       */     //   441: iconst_m1
/*       */     //   442: ixor
/*       */     //   443: iload #17
/*       */     //   445: iand
/*       */     //   446: ior
/*       */     //   447: sipush #-7925
/*       */     //   450: sipush #7815
/*       */     //   453: istore #17
/*       */     //   455: istore #16
/*       */     //   457: iload #16
/*       */     //   459: iload #17
/*       */     //   461: iconst_m1
/*       */     //   462: ixor
/*       */     //   463: iand
/*       */     //   464: iload #16
/*       */     //   466: iconst_m1
/*       */     //   467: ixor
/*       */     //   468: iload #17
/*       */     //   470: iand
/*       */     //   471: ior
/*       */     //   472: bastore
/*       */     //   473: aload #4
/*       */     //   475: sipush #27147
/*       */     //   478: sipush #27148
/*       */     //   481: istore #17
/*       */     //   483: istore #16
/*       */     //   485: iload #16
/*       */     //   487: iload #17
/*       */     //   489: ior
/*       */     //   490: iload #16
/*       */     //   492: iload #17
/*       */     //   494: iand
/*       */     //   495: iconst_m1
/*       */     //   496: ixor
/*       */     //   497: iand
/*       */     //   498: sipush #5378
/*       */     //   501: istore #15
/*       */     //   503: ldc2_w -8727891827765537502
/*       */     //   506: l2i
/*       */     //   507: iload #15
/*       */     //   509: istore #17
/*       */     //   511: istore #16
/*       */     //   513: iload #16
/*       */     //   515: iload #17
/*       */     //   517: ior
/*       */     //   518: iload #16
/*       */     //   520: iload #17
/*       */     //   522: iand
/*       */     //   523: isub
/*       */     //   524: bastore
/*       */     //   525: aload #4
/*       */     //   527: sipush #27261
/*       */     //   530: ldc2_w 4555560425185372788
/*       */     //   533: l2i
/*       */     //   534: istore #17
/*       */     //   536: istore #16
/*       */     //   538: iload #16
/*       */     //   540: iload #17
/*       */     //   542: iconst_m1
/*       */     //   543: ixor
/*       */     //   544: iand
/*       */     //   545: iload #16
/*       */     //   547: iconst_m1
/*       */     //   548: ixor
/*       */     //   549: iload #17
/*       */     //   551: iand
/*       */     //   552: ior
/*       */     //   553: sipush #-6819
/*       */     //   556: istore #15
/*       */     //   558: iload #15
/*       */     //   560: sipush #6791
/*       */     //   563: istore #17
/*       */     //   565: istore #16
/*       */     //   567: iload #16
/*       */     //   569: iload #17
/*       */     //   571: ior
/*       */     //   572: iload #16
/*       */     //   574: iload #17
/*       */     //   576: iand
/*       */     //   577: isub
/*       */     //   578: bastore
/*       */     //   579: aload #4
/*       */     //   581: sipush #7037
/*       */     //   584: sipush #7035
/*       */     //   587: istore #17
/*       */     //   589: istore #16
/*       */     //   591: iload #16
/*       */     //   593: iload #17
/*       */     //   595: ior
/*       */     //   596: iload #16
/*       */     //   598: iload #17
/*       */     //   600: iand
/*       */     //   601: iconst_m1
/*       */     //   602: ixor
/*       */     //   603: iand
/*       */     //   604: sipush #-1648
/*       */     //   607: istore #15
/*       */     //   609: iload #15
/*       */     //   611: sipush #1644
/*       */     //   614: istore #17
/*       */     //   616: istore #16
/*       */     //   618: iload #16
/*       */     //   620: iload #17
/*       */     //   622: ior
/*       */     //   623: iload #16
/*       */     //   625: iload #17
/*       */     //   627: iand
/*       */     //   628: iconst_m1
/*       */     //   629: ixor
/*       */     //   630: iand
/*       */     //   631: bastore
/*       */     //   632: aload #4
/*       */     //   634: sipush #19109
/*       */     //   637: istore #15
/*       */     //   639: ldc2_w 8650731091025480363
/*       */     //   642: l2i
/*       */     //   643: iload #15
/*       */     //   645: istore #17
/*       */     //   647: istore #16
/*       */     //   649: iload #16
/*       */     //   651: iload #17
/*       */     //   653: iconst_m1
/*       */     //   654: ixor
/*       */     //   655: iand
/*       */     //   656: iload #16
/*       */     //   658: iconst_m1
/*       */     //   659: ixor
/*       */     //   660: iload #17
/*       */     //   662: iand
/*       */     //   663: ior
/*       */     //   664: sipush #16726
/*       */     //   667: istore #15
/*       */     //   669: ldc2_w 940697141762867497
/*       */     //   672: l2i
/*       */     //   673: iload #15
/*       */     //   675: istore #17
/*       */     //   677: istore #16
/*       */     //   679: iload #16
/*       */     //   681: iload #17
/*       */     //   683: iconst_m1
/*       */     //   684: ixor
/*       */     //   685: iand
/*       */     //   686: iload #16
/*       */     //   688: iconst_m1
/*       */     //   689: ixor
/*       */     //   690: iload #17
/*       */     //   692: iand
/*       */     //   693: ior
/*       */     //   694: bastore
/*       */     //   695: aload #4
/*       */     //   697: ldc2_w 6750097437072820036
/*       */     //   700: l2i
/*       */     //   701: sipush #1856
/*       */     //   704: istore #17
/*       */     //   706: istore #16
/*       */     //   708: iload #16
/*       */     //   710: iload #17
/*       */     //   712: ior
/*       */     //   713: iload #16
/*       */     //   715: iload #17
/*       */     //   717: iand
/*       */     //   718: isub
/*       */     //   719: ldc2_w 2834763348338298889
/*       */     //   722: l2i
/*       */     //   723: sipush #20523
/*       */     //   726: istore #17
/*       */     //   728: istore #16
/*       */     //   730: iload #16
/*       */     //   732: iload #17
/*       */     //   734: ior
/*       */     //   735: iload #16
/*       */     //   737: iload #17
/*       */     //   739: iand
/*       */     //   740: iconst_m1
/*       */     //   741: ixor
/*       */     //   742: iand
/*       */     //   743: bastore
/*       */     //   744: aload #4
/*       */     //   746: sipush #6717
/*       */     //   749: istore #15
/*       */     //   751: iload #15
/*       */     //   753: ldc2_w 3940132053080611376
/*       */     //   756: l2i
/*       */     //   757: istore #17
/*       */     //   759: istore #16
/*       */     //   761: iload #16
/*       */     //   763: iload #17
/*       */     //   765: ior
/*       */     //   766: iload #16
/*       */     //   768: iconst_m1
/*       */     //   769: ixor
/*       */     //   770: iload #17
/*       */     //   772: iconst_m1
/*       */     //   773: ixor
/*       */     //   774: ior
/*       */     //   775: iand
/*       */     //   776: ldc2_w -3391404016276577372
/*       */     //   779: l2i
/*       */     //   780: sipush #-25479
/*       */     //   783: istore #17
/*       */     //   785: istore #16
/*       */     //   787: iload #16
/*       */     //   789: iload #17
/*       */     //   791: ior
/*       */     //   792: iload #16
/*       */     //   794: iload #17
/*       */     //   796: iand
/*       */     //   797: iconst_m1
/*       */     //   798: ixor
/*       */     //   799: iand
/*       */     //   800: bastore
/*       */     //   801: aload #4
/*       */     //   803: sipush #18117
/*       */     //   806: istore #15
/*       */     //   808: ldc2_w -5911517003947948339
/*       */     //   811: l2i
/*       */     //   812: iload #15
/*       */     //   814: istore #17
/*       */     //   816: istore #16
/*       */     //   818: iload #16
/*       */     //   820: iload #17
/*       */     //   822: ior
/*       */     //   823: iload #16
/*       */     //   825: iload #17
/*       */     //   827: iand
/*       */     //   828: isub
/*       */     //   829: sipush #18108
/*       */     //   832: istore #15
/*       */     //   834: ldc2_w 7645231353851561637
/*       */     //   837: l2i
/*       */     //   838: iload #15
/*       */     //   840: istore #17
/*       */     //   842: istore #16
/*       */     //   844: iload #16
/*       */     //   846: iload #17
/*       */     //   848: iconst_m1
/*       */     //   849: ixor
/*       */     //   850: iand
/*       */     //   851: iload #16
/*       */     //   853: iconst_m1
/*       */     //   854: ixor
/*       */     //   855: iload #17
/*       */     //   857: iand
/*       */     //   858: ior
/*       */     //   859: bastore
/*       */     //   860: aload_3
/*       */     //   861: arraylength
/*       */     //   862: sipush #6009
/*       */     //   865: istore #15
/*       */     //   867: iload #15
/*       */     //   869: sipush #5977
/*       */     //   872: istore #17
/*       */     //   874: istore #16
/*       */     //   876: iload #16
/*       */     //   878: iload #17
/*       */     //   880: ior
/*       */     //   881: iload #16
/*       */     //   883: iconst_m1
/*       */     //   884: ixor
/*       */     //   885: iload #17
/*       */     //   887: iconst_m1
/*       */     //   888: ixor
/*       */     //   889: ior
/*       */     //   890: iand
/*       */     //   891: istore #17
/*       */     //   893: istore #16
/*       */     //   895: iconst_2
/*       */     //   896: iload #16
/*       */     //   898: iload #17
/*       */     //   900: iconst_m1
/*       */     //   901: ixor
/*       */     //   902: iand
/*       */     //   903: imul
/*       */     //   904: iload #16
/*       */     //   906: iload #17
/*       */     //   908: ixor
/*       */     //   909: isub
/*       */     //   910: newarray byte
/*       */     //   912: astore #5
/*       */     //   914: aload_3
/*       */     //   915: sipush #11016
/*       */     //   918: istore #15
/*       */     //   920: iload #15
/*       */     //   922: ldc2_w -1060862398195291384
/*       */     //   925: l2i
/*       */     //   926: istore #17
/*       */     //   928: istore #16
/*       */     //   930: iload #16
/*       */     //   932: iload #17
/*       */     //   934: ior
/*       */     //   935: iload #16
/*       */     //   937: iconst_m1
/*       */     //   938: ixor
/*       */     //   939: iload #17
/*       */     //   941: iconst_m1
/*       */     //   942: ixor
/*       */     //   943: ior
/*       */     //   944: iand
/*       */     //   945: aload #4
/*       */     //   947: ldc2_w -1050413283929986701
/*       */     //   950: l2i
/*       */     //   951: sipush #26995
/*       */     //   954: istore #17
/*       */     //   956: istore #16
/*       */     //   958: iload #16
/*       */     //   960: iload #17
/*       */     //   962: iconst_m1
/*       */     //   963: ixor
/*       */     //   964: iand
/*       */     //   965: iload #16
/*       */     //   967: iconst_m1
/*       */     //   968: ixor
/*       */     //   969: iload #17
/*       */     //   971: iand
/*       */     //   972: ior
/*       */     //   973: sipush #5017
/*       */     //   976: sipush #5001
/*       */     //   979: istore #17
/*       */     //   981: istore #16
/*       */     //   983: iload #16
/*       */     //   985: iload #17
/*       */     //   987: ior
/*       */     //   988: iload #16
/*       */     //   990: iload #17
/*       */     //   992: iand
/*       */     //   993: iconst_m1
/*       */     //   994: ixor
/*       */     //   995: iand
/*       */     //   996: <illegal opcode> bcek : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*       */     //   1001: aload_3
/*       */     //   1002: sipush #17975
/*       */     //   1005: istore #15
/*       */     //   1007: iload #15
/*       */     //   1009: sipush #17943
/*       */     //   1012: istore #17
/*       */     //   1014: istore #16
/*       */     //   1016: iload #16
/*       */     //   1018: iload #17
/*       */     //   1020: iconst_m1
/*       */     //   1021: ixor
/*       */     //   1022: iand
/*       */     //   1023: iload #16
/*       */     //   1025: iconst_m1
/*       */     //   1026: ixor
/*       */     //   1027: iload #17
/*       */     //   1029: iand
/*       */     //   1030: ior
/*       */     //   1031: aload #5
/*       */     //   1033: sipush #17820
/*       */     //   1036: ldc2_w 8849749640615052700
/*       */     //   1039: l2i
/*       */     //   1040: istore #17
/*       */     //   1042: istore #16
/*       */     //   1044: iload #16
/*       */     //   1046: iload #17
/*       */     //   1048: ior
/*       */     //   1049: iload #16
/*       */     //   1051: iconst_m1
/*       */     //   1052: ixor
/*       */     //   1053: iload #17
/*       */     //   1055: iconst_m1
/*       */     //   1056: ixor
/*       */     //   1057: ior
/*       */     //   1058: iand
/*       */     //   1059: aload_3
/*       */     //   1060: arraylength
/*       */     //   1061: sipush #21429
/*       */     //   1064: ldc2_w 669606541275452309
/*       */     //   1067: l2i
/*       */     //   1068: istore #17
/*       */     //   1070: istore #16
/*       */     //   1072: iload #16
/*       */     //   1074: iload #17
/*       */     //   1076: ior
/*       */     //   1077: iload #16
/*       */     //   1079: iconst_m1
/*       */     //   1080: ixor
/*       */     //   1081: iload #17
/*       */     //   1083: iconst_m1
/*       */     //   1084: ixor
/*       */     //   1085: ior
/*       */     //   1086: iand
/*       */     //   1087: istore #17
/*       */     //   1089: istore #16
/*       */     //   1091: iload #16
/*       */     //   1093: iload #17
/*       */     //   1095: iconst_m1
/*       */     //   1096: ixor
/*       */     //   1097: iadd
/*       */     //   1098: iconst_1
/*       */     //   1099: iadd
/*       */     //   1100: <illegal opcode> bcel : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*       */     //   1105: new javax/crypto/spec/PBEKeySpec
/*       */     //   1108: dup
/*       */     //   1109: aload_1
/*       */     //   1110: <illegal opcode> bcem : (Ljava/lang/Object;)[C
/*       */     //   1115: checkcast [C
/*       */     //   1118: aload #4
/*       */     //   1120: sipush #17332
/*       */     //   1123: istore #15
/*       */     //   1125: ldc2_w -5083286754811689994
/*       */     //   1128: l2i
/*       */     //   1129: iload #15
/*       */     //   1131: istore #17
/*       */     //   1133: istore #16
/*       */     //   1135: iload #16
/*       */     //   1137: iload #17
/*       */     //   1139: ior
/*       */     //   1140: iload #16
/*       */     //   1142: iload #17
/*       */     //   1144: iand
/*       */     //   1145: iconst_m1
/*       */     //   1146: ixor
/*       */     //   1147: iand
/*       */     //   1148: sipush #21879
/*       */     //   1151: ldc2_w 4279290527930668151
/*       */     //   1154: l2i
/*       */     //   1155: istore #17
/*       */     //   1157: istore #16
/*       */     //   1159: iload #16
/*       */     //   1161: iload #17
/*       */     //   1163: ior
/*       */     //   1164: iload #16
/*       */     //   1166: iconst_m1
/*       */     //   1167: ixor
/*       */     //   1168: iload #17
/*       */     //   1170: iconst_m1
/*       */     //   1171: ixor
/*       */     //   1172: ior
/*       */     //   1173: iand
/*       */     //   1174: invokespecial <init> : ([C[BII)V
/*       */     //   1177: astore #6
/*       */     //   1179: ldc_w -754933464
/*       */     //   1182: ldc2_w -3899536838242590305
/*       */     //   1185: l2i
/*       */     //   1186: istore #17
/*       */     //   1188: istore #16
/*       */     //   1190: iload #16
/*       */     //   1192: iload #17
/*       */     //   1194: ior
/*       */     //   1195: iload #16
/*       */     //   1197: iload #17
/*       */     //   1199: iand
/*       */     //   1200: iconst_m1
/*       */     //   1201: ixor
/*       */     //   1202: iand
/*       */     //   1203: ldc_w 1654409728
/*       */     //   1206: istore #15
/*       */     //   1208: ldc2_w -6146809902426737926
/*       */     //   1211: l2i
/*       */     //   1212: iload #15
/*       */     //   1214: istore #17
/*       */     //   1216: istore #16
/*       */     //   1218: iload #16
/*       */     //   1220: iload #17
/*       */     //   1222: ior
/*       */     //   1223: iload #16
/*       */     //   1225: iload #17
/*       */     //   1227: iand
/*       */     //   1228: iconst_m1
/*       */     //   1229: ixor
/*       */     //   1230: iand
/*       */     //   1231: <illegal opcode> bcen : (II)Ljava/lang/String;
/*       */     //   1236: <illegal opcode> bceo : (Ljava/lang/String;)Ljavax/crypto/SecretKeyFactory;
/*       */     //   1241: astore #7
/*       */     //   1243: aload #7
/*       */     //   1245: aload #6
/*       */     //   1247: <illegal opcode> bcep : (Ljava/lang/Object;Ljava/security/spec/KeySpec;)Ljavax/crypto/SecretKey;
/*       */     //   1252: <illegal opcode> bceq : (Ljava/lang/Object;)[B
/*       */     //   1257: checkcast [B
/*       */     //   1260: astore #8
/*       */     //   1262: new javax/crypto/spec/SecretKeySpec
/*       */     //   1265: dup
/*       */     //   1266: aload #8
/*       */     //   1268: ldc2_w 6900265415184745443
/*       */     //   1271: l2i
/*       */     //   1272: ldc_w -27294764
/*       */     //   1275: istore #17
/*       */     //   1277: istore #16
/*       */     //   1279: iload #16
/*       */     //   1281: iload #17
/*       */     //   1283: ior
/*       */     //   1284: iload #16
/*       */     //   1286: iload #17
/*       */     //   1288: iand
/*       */     //   1289: iconst_m1
/*       */     //   1290: ixor
/*       */     //   1291: iand
/*       */     //   1292: ldc2_w 5958692594014755413
/*       */     //   1295: l2i
/*       */     //   1296: ldc_w 1838718402
/*       */     //   1299: istore #17
/*       */     //   1301: istore #16
/*       */     //   1303: iload #16
/*       */     //   1305: iload #17
/*       */     //   1307: iconst_m1
/*       */     //   1308: ixor
/*       */     //   1309: iand
/*       */     //   1310: iload #16
/*       */     //   1312: iconst_m1
/*       */     //   1313: ixor
/*       */     //   1314: iload #17
/*       */     //   1316: iand
/*       */     //   1317: ior
/*       */     //   1318: <illegal opcode> bcer : (II)Ljava/lang/String;
/*       */     //   1323: invokespecial <init> : ([BLjava/lang/String;)V
/*       */     //   1326: astore #9
/*       */     //   1328: ldc_w -385881726
/*       */     //   1331: sipush #26919
/*       */     //   1334: istore #17
/*       */     //   1336: istore #16
/*       */     //   1338: iload #16
/*       */     //   1340: iload #17
/*       */     //   1342: iconst_m1
/*       */     //   1343: ixor
/*       */     //   1344: iand
/*       */     //   1345: iload #16
/*       */     //   1347: iconst_m1
/*       */     //   1348: ixor
/*       */     //   1349: iload #17
/*       */     //   1351: iand
/*       */     //   1352: ior
/*       */     //   1353: ldc_w -75421864
/*       */     //   1356: istore #15
/*       */     //   1358: ldc2_w 8335635234875793378
/*       */     //   1361: l2i
/*       */     //   1362: iload #15
/*       */     //   1364: istore #17
/*       */     //   1366: istore #16
/*       */     //   1368: iload #16
/*       */     //   1370: iload #17
/*       */     //   1372: ior
/*       */     //   1373: iload #16
/*       */     //   1375: iconst_m1
/*       */     //   1376: ixor
/*       */     //   1377: iload #17
/*       */     //   1379: iconst_m1
/*       */     //   1380: ixor
/*       */     //   1381: ior
/*       */     //   1382: iand
/*       */     //   1383: <illegal opcode> bces : (II)Ljava/lang/String;
/*       */     //   1388: <illegal opcode> bcet : (Ljava/lang/String;)Ljavax/crypto/Cipher;
/*       */     //   1393: astore #10
/*       */     //   1395: aload #10
/*       */     //   1397: sipush #19795
/*       */     //   1400: ldc2_w 409178751151131985
/*       */     //   1403: l2i
/*       */     //   1404: istore #17
/*       */     //   1406: istore #16
/*       */     //   1408: iload #16
/*       */     //   1410: iload #17
/*       */     //   1412: iconst_m1
/*       */     //   1413: ixor
/*       */     //   1414: iand
/*       */     //   1415: iload #16
/*       */     //   1417: iconst_m1
/*       */     //   1418: ixor
/*       */     //   1419: iload #17
/*       */     //   1421: iand
/*       */     //   1422: ior
/*       */     //   1423: aload #9
/*       */     //   1425: new javax/crypto/spec/IvParameterSpec
/*       */     //   1428: dup
/*       */     //   1429: aload_2
/*       */     //   1430: invokespecial <init> : ([B)V
/*       */     //   1433: <illegal opcode> bceu : (Ljava/lang/Object;ILjava/security/Key;Ljava/security/spec/AlgorithmParameterSpec;)V
/*       */     //   1438: aload #10
/*       */     //   1440: aload #5
/*       */     //   1442: <illegal opcode> bcev : (Ljava/lang/Object;[B)[B
/*       */     //   1447: checkcast [B
/*       */     //   1450: astore #11
/*       */     //   1452: new java/lang/String
/*       */     //   1455: dup
/*       */     //   1456: aload #11
/*       */     //   1458: ldc2_w 2759817887009999802
/*       */     //   1461: l2i
/*       */     //   1462: ldc_w -692417647
/*       */     //   1465: istore #17
/*       */     //   1467: istore #16
/*       */     //   1469: iload #16
/*       */     //   1471: iload #17
/*       */     //   1473: iconst_m1
/*       */     //   1474: ixor
/*       */     //   1475: iand
/*       */     //   1476: iload #16
/*       */     //   1478: iconst_m1
/*       */     //   1479: ixor
/*       */     //   1480: iload #17
/*       */     //   1482: iand
/*       */     //   1483: ior
/*       */     //   1484: ldc_w -2050849953
/*       */     //   1487: sipush #29394
/*       */     //   1490: istore #17
/*       */     //   1492: istore #16
/*       */     //   1494: iload #16
/*       */     //   1496: iload #17
/*       */     //   1498: ior
/*       */     //   1499: iload #16
/*       */     //   1501: iload #17
/*       */     //   1503: iand
/*       */     //   1504: isub
/*       */     //   1505: <illegal opcode> bcew : (II)Ljava/lang/String;
/*       */     //   1510: invokespecial <init> : ([BLjava/lang/String;)V
/*       */     //   1513: areturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #7160	-> 0
/*       */     //   #714	-> 15
/*       */     //   #56893	-> 47
/*       */     //   #25803	-> 860
/*       */     //   #25148	-> 914
/*       */     //   #5341	-> 1001
/*       */     //   #2934	-> 1105
/*       */     //   #19495	-> 1179
/*       */     //   #3011	-> 1243
/*       */     //   #20898	-> 1262
/*       */     //   #44367	-> 1328
/*       */     //   #45850	-> 1395
/*       */     //   #5449	-> 1438
/*       */     //   #30789	-> 1452
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getSpawned() {
/*  7283 */     return this.spawned;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getVelocityTicks() {
/*  7294 */     return this.velocityTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void saveData() {
/*       */     // Byte code:
/*       */     //   0: <illegal opcode> bbsw : ()Lme/levansj01/on;
/*       */     //   5: astore_1
/*       */     //   6: aload_1
/*       */     //   7: <illegal opcode> bbsx : (Ljava/lang/Object;)Z
/*       */     //   12: ifne -> 18
/*       */     //   15: goto -> 62
/*       */     //   18: aload_1
/*       */     //   19: <illegal opcode> bbsy : (Ljava/lang/Object;)Lme/levansj01/kh;
/*       */     //   24: <illegal opcode> bbsz : (Ljava/lang/Object;)Z
/*       */     //   29: ifne -> 35
/*       */     //   32: goto -> 62
/*       */     //   35: aload_1
/*       */     //   36: <illegal opcode> bbta : (Ljava/lang/Object;)Lme/levansj01/gp;
/*       */     //   41: astore_2
/*       */     //   42: aload_0
/*       */     //   43: getfield banned : Z
/*       */     //   46: ifne -> 52
/*       */     //   49: goto -> 55
/*       */     //   52: goto -> 62
/*       */     //   55: aload_2
/*       */     //   56: aload_0
/*       */     //   57: <illegal opcode> bbtb : (Ljava/lang/Object;Lme/levansj01/dc;)V
/*       */     //   62: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #7818	-> 0
/*       */     //   #36118	-> 6
/*       */     //   #52208	-> 35
/*       */     //   #36269	-> 42
/*       */     //   #41509	-> 55
/*       */     //   #24887	-> 62
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   42	20	2	brzg	Lme/levansj01/gp;
/*       */     //   0	63	0	brzh	Lme/levansj01/dc;
/*       */     //   6	57	1	brzi	Lme/levansj01/on;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public boolean hasLag(long brzx) {
/*  8004 */     if (this.lastFlying == 0L || this.lastDelayed == 0L || brzx - this.lastDelayed >= 110L) { char c3 = '撰', c5 = '撰', c4 = c3; return (c4 | c5) & (c4 & c5 ^ 0xFFFFFFFF); }  char c1 = '⮳'; int i = (int)4023825628351441842L; char c2 = c1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getLastTeleportTicks() {
/*  8327 */     return this.lastTeleportTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public nc getClientData() {
/*  9145 */     return this.clientData;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(l<?> brtq) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: aload_1
/*       */     //   2: <illegal opcode> bbdq : (Ljava/lang/Object;)Z
/*       */     //   7: putfield clientIsOnGround : Z
/*       */     //   10: aload_0
/*       */     //   11: getfield clientIsOnGround : Z
/*       */     //   14: ifeq -> 20
/*       */     //   17: goto -> 74
/*       */     //   20: aload_0
/*       */     //   21: dup
/*       */     //   22: getfield clientAirTicks : I
/*       */     //   25: sipush #10872
/*       */     //   28: istore #23
/*       */     //   30: iload #23
/*       */     //   32: ldc2_w 777072824954137209
/*       */     //   35: l2i
/*       */     //   36: istore #25
/*       */     //   38: istore #24
/*       */     //   40: iload #24
/*       */     //   42: iload #25
/*       */     //   44: ior
/*       */     //   45: iload #24
/*       */     //   47: iconst_m1
/*       */     //   48: ixor
/*       */     //   49: iload #25
/*       */     //   51: iconst_m1
/*       */     //   52: ixor
/*       */     //   53: ior
/*       */     //   54: iand
/*       */     //   55: istore #25
/*       */     //   57: istore #24
/*       */     //   59: iload #24
/*       */     //   61: iload #25
/*       */     //   63: iconst_m1
/*       */     //   64: ixor
/*       */     //   65: isub
/*       */     //   66: iconst_1
/*       */     //   67: isub
/*       */     //   68: putfield clientAirTicks : I
/*       */     //   71: goto -> 104
/*       */     //   74: aload_0
/*       */     //   75: ldc2_w -1552809970913760621
/*       */     //   78: l2i
/*       */     //   79: sipush #10899
/*       */     //   82: istore #25
/*       */     //   84: istore #24
/*       */     //   86: iload #24
/*       */     //   88: iload #25
/*       */     //   90: ior
/*       */     //   91: iload #24
/*       */     //   93: iconst_m1
/*       */     //   94: ixor
/*       */     //   95: iload #25
/*       */     //   97: iconst_m1
/*       */     //   98: ixor
/*       */     //   99: ior
/*       */     //   100: iand
/*       */     //   101: putfield clientAirTicks : I
/*       */     //   104: aload_0
/*       */     //   105: getfield timestamp : J
/*       */     //   108: lstore #4
/*       */     //   110: aload_0
/*       */     //   111: <illegal opcode> bbdr : ()J
/*       */     //   116: putfield nanos : J
/*       */     //   119: aload_0
/*       */     //   120: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   123: <illegal opcode> bbds : (Ljava/lang/Object;)V
/*       */     //   128: aload_0
/*       */     //   129: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   132: getstatic me/levansj01/ij.ATTACKS_IN_LAST : Lme/levansj01/ij;
/*       */     //   135: aload_0
/*       */     //   136: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   139: getstatic me/levansj01/ij.ATTACKS : Lme/levansj01/ij;
/*       */     //   142: <illegal opcode> bbdt : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   147: <illegal opcode> bbdu : (Ljava/lang/Object;Lme/levansj01/ij;I)V
/*       */     //   152: aload_0
/*       */     //   153: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   156: getstatic me/levansj01/ij.ATTACKS : Lme/levansj01/ij;
/*       */     //   159: <illegal opcode> bbdv : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   164: aload_1
/*       */     //   165: <illegal opcode> bbdw : (Ljava/lang/Object;)Z
/*       */     //   170: ifne -> 176
/*       */     //   173: goto -> 205
/*       */     //   176: aload_1
/*       */     //   177: aload_0
/*       */     //   178: getfield currentLocation2 : Lme/levansj01/nq;
/*       */     //   181: <illegal opcode> bbdx : (Ljava/lang/Object;Lme/levansj01/ir;)Z
/*       */     //   186: ifeq -> 192
/*       */     //   189: goto -> 205
/*       */     //   192: aload_0
/*       */     //   193: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   196: getstatic me/levansj01/ij.MOVES_SINCE_TELEPORT : Lme/levansj01/ij;
/*       */     //   199: <illegal opcode> bbdy : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   204: pop
/*       */     //   205: aload_0
/*       */     //   206: getfield teleportHandler : Lme/levansj01/jn;
/*       */     //   209: ifnull -> 228
/*       */     //   212: aload_1
/*       */     //   213: aload_0
/*       */     //   214: getfield teleportHandler : Lme/levansj01/jn;
/*       */     //   217: aload_1
/*       */     //   218: <illegal opcode> bbdz : (Ljava/lang/Object;Lme/levansj01/li;)Z
/*       */     //   223: <illegal opcode> bbea : (Ljava/lang/Object;Z)V
/*       */     //   228: aload_1
/*       */     //   229: <illegal opcode> bbeb : (Ljava/lang/Object;)Z
/*       */     //   234: ifeq -> 240
/*       */     //   237: goto -> 293
/*       */     //   240: aload_0
/*       */     //   241: dup
/*       */     //   242: getfield ticks : I
/*       */     //   245: sipush #8729
/*       */     //   248: istore #23
/*       */     //   250: ldc2_w 986977956997898776
/*       */     //   253: l2i
/*       */     //   254: iload #23
/*       */     //   256: istore #25
/*       */     //   258: istore #24
/*       */     //   260: iload #24
/*       */     //   262: iload #25
/*       */     //   264: ior
/*       */     //   265: iload #24
/*       */     //   267: iconst_m1
/*       */     //   268: ixor
/*       */     //   269: iload #25
/*       */     //   271: iconst_m1
/*       */     //   272: ixor
/*       */     //   273: ior
/*       */     //   274: iand
/*       */     //   275: istore #25
/*       */     //   277: istore #24
/*       */     //   279: iload #24
/*       */     //   281: iload #25
/*       */     //   283: ior
/*       */     //   284: iload #24
/*       */     //   286: iload #25
/*       */     //   288: iand
/*       */     //   289: iadd
/*       */     //   290: putfield ticks : I
/*       */     //   293: aload_0
/*       */     //   294: getfield teleports : Ljava/util/Queue;
/*       */     //   297: <illegal opcode> bbec : (Ljava/lang/Object;)Ljava/util/Iterator;
/*       */     //   302: astore #6
/*       */     //   304: aload #6
/*       */     //   306: <illegal opcode> bbed : (Ljava/lang/Object;)Z
/*       */     //   311: ifne -> 317
/*       */     //   314: goto -> 670
/*       */     //   317: aload #6
/*       */     //   319: <illegal opcode> bbee : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   324: checkcast me/levansj01/bi
/*       */     //   327: astore #7
/*       */     //   329: aload #7
/*       */     //   331: aload_1
/*       */     //   332: <illegal opcode> bbef : (Ljava/lang/Object;Lme/levansj01/l;)Z
/*       */     //   337: ifne -> 343
/*       */     //   340: goto -> 421
/*       */     //   343: aload_0
/*       */     //   344: getfield teleports : Ljava/util/Queue;
/*       */     //   347: aload #7
/*       */     //   349: <illegal opcode> bbeg : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   354: pop
/*       */     //   355: aload_0
/*       */     //   356: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   359: getstatic me/levansj01/ij.TELEPORT : Lme/levansj01/ij;
/*       */     //   362: <illegal opcode> bbeh : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   367: aload_0
/*       */     //   368: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   371: getstatic me/levansj01/ij.MOVES_SINCE_TELEPORT : Lme/levansj01/ij;
/*       */     //   374: <illegal opcode> bbei : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   379: aload_0
/*       */     //   380: aload #7
/*       */     //   382: putfield lastTeleport2 : Lme/levansj01/bi;
/*       */     //   385: aload_0
/*       */     //   386: getfield checkData : Lme/levansj01/fc;
/*       */     //   389: <illegal opcode> bbej : (Ljava/lang/Object;)Lme/levansj01/br;
/*       */     //   394: ifnonnull -> 400
/*       */     //   397: goto -> 670
/*       */     //   400: aload_0
/*       */     //   401: getfield checkData : Lme/levansj01/fc;
/*       */     //   404: <illegal opcode> bbek : (Ljava/lang/Object;)Lme/levansj01/br;
/*       */     //   409: lload #4
/*       */     //   411: aload #7
/*       */     //   413: <illegal opcode> bbel : (Ljava/lang/Object;JLme/levansj01/bi;)V
/*       */     //   418: goto -> 670
/*       */     //   421: lload #4
/*       */     //   423: aload #7
/*       */     //   425: <illegal opcode> bbem : (Ljava/lang/Object;)J
/*       */     //   430: lsub
/*       */     //   431: aload_0
/*       */     //   432: <illegal opcode> bben : (Ljava/lang/Object;)I
/*       */     //   437: sipush #20535
/*       */     //   440: istore #23
/*       */     //   442: ldc2_w 942069787540870111
/*       */     //   445: l2i
/*       */     //   446: iload #23
/*       */     //   448: istore #25
/*       */     //   450: istore #24
/*       */     //   452: iload #24
/*       */     //   454: iload #25
/*       */     //   456: ior
/*       */     //   457: iload #24
/*       */     //   459: iconst_m1
/*       */     //   460: ixor
/*       */     //   461: iload #25
/*       */     //   463: iconst_m1
/*       */     //   464: ixor
/*       */     //   465: ior
/*       */     //   466: iand
/*       */     //   467: istore #25
/*       */     //   469: istore #24
/*       */     //   471: iload #24
/*       */     //   473: iload #25
/*       */     //   475: ixor
/*       */     //   476: iconst_2
/*       */     //   477: iload #24
/*       */     //   479: iload #25
/*       */     //   481: iand
/*       */     //   482: imul
/*       */     //   483: iadd
/*       */     //   484: sipush #3154
/*       */     //   487: istore #23
/*       */     //   489: iload #23
/*       */     //   491: sipush #3152
/*       */     //   494: istore #25
/*       */     //   496: istore #24
/*       */     //   498: iload #24
/*       */     //   500: iload #25
/*       */     //   502: ior
/*       */     //   503: iload #24
/*       */     //   505: iload #25
/*       */     //   507: iand
/*       */     //   508: iconst_m1
/*       */     //   509: ixor
/*       */     //   510: iand
/*       */     //   511: imul
/*       */     //   512: i2l
/*       */     //   513: lcmp
/*       */     //   514: ifgt -> 520
/*       */     //   517: goto -> 304
/*       */     //   520: aload_0
/*       */     //   521: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   524: getstatic me/levansj01/ij.TOTAL : Lme/levansj01/ij;
/*       */     //   527: <illegal opcode> bbeo : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   532: aload #7
/*       */     //   534: <illegal opcode> bbep : (Ljava/lang/Object;)I
/*       */     //   539: istore #25
/*       */     //   541: istore #24
/*       */     //   543: iload #24
/*       */     //   545: iload #25
/*       */     //   547: ixor
/*       */     //   548: iconst_2
/*       */     //   549: iload #24
/*       */     //   551: iconst_m1
/*       */     //   552: ixor
/*       */     //   553: iload #25
/*       */     //   555: iand
/*       */     //   556: imul
/*       */     //   557: isub
/*       */     //   558: sipush #23044
/*       */     //   561: istore #23
/*       */     //   563: ldc2_w 3833603944878201350
/*       */     //   566: l2i
/*       */     //   567: iload #23
/*       */     //   569: istore #25
/*       */     //   571: istore #24
/*       */     //   573: iload #24
/*       */     //   575: iload #25
/*       */     //   577: ior
/*       */     //   578: iload #24
/*       */     //   580: iload #25
/*       */     //   582: iand
/*       */     //   583: isub
/*       */     //   584: aload_0
/*       */     //   585: <illegal opcode> bbeq : (Ljava/lang/Object;)I
/*       */     //   590: sipush #10231
/*       */     //   593: istore #23
/*       */     //   595: ldc2_w 6042347875357173731
/*       */     //   598: l2i
/*       */     //   599: iload #23
/*       */     //   601: istore #25
/*       */     //   603: istore #24
/*       */     //   605: iload #24
/*       */     //   607: iload #25
/*       */     //   609: ior
/*       */     //   610: iload #24
/*       */     //   612: iconst_m1
/*       */     //   613: ixor
/*       */     //   614: iload #25
/*       */     //   616: iconst_m1
/*       */     //   617: ixor
/*       */     //   618: ior
/*       */     //   619: iand
/*       */     //   620: istore #25
/*       */     //   622: istore #24
/*       */     //   624: iload #24
/*       */     //   626: iload #25
/*       */     //   628: iconst_m1
/*       */     //   629: ixor
/*       */     //   630: isub
/*       */     //   631: iconst_1
/*       */     //   632: isub
/*       */     //   633: imul
/*       */     //   634: if_icmple -> 640
/*       */     //   637: goto -> 643
/*       */     //   640: goto -> 304
/*       */     //   643: aload_0
/*       */     //   644: getfield teleports : Ljava/util/Queue;
/*       */     //   647: aload #7
/*       */     //   649: <illegal opcode> bber : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   654: pop
/*       */     //   655: goto -> 667
/*       */     //   658: astore #8
/*       */     //   660: aload #8
/*       */     //   662: <illegal opcode> bbes : (Ljava/lang/Object;)V
/*       */     //   667: goto -> 304
/*       */     //   670: aload_1
/*       */     //   671: <illegal opcode> bbet : (Ljava/lang/Object;)Z
/*       */     //   676: ifeq -> 682
/*       */     //   679: goto -> 696
/*       */     //   682: aload_0
/*       */     //   683: getfield start : Ljava/util/Queue;
/*       */     //   686: <illegal opcode> get : ()Ljava/util/function/Supplier;
/*       */     //   691: <illegal opcode> bbeu : (Ljava/util/Queue;Ljava/util/function/Supplier;)V
/*       */     //   696: <illegal opcode> bbev : ()Lme/levansj01/verus/compat/NMSManager;
/*       */     //   701: astore #6
/*       */     //   703: aload_0
/*       */     //   704: getfield totalTicks : I
/*       */     //   707: sipush #12782
/*       */     //   710: istore #23
/*       */     //   712: ldc2_w 6425095170135437803
/*       */     //   715: l2i
/*       */     //   716: iload #23
/*       */     //   718: istore #25
/*       */     //   720: istore #24
/*       */     //   722: iload #24
/*       */     //   724: iload #25
/*       */     //   726: ior
/*       */     //   727: iload #24
/*       */     //   729: iload #25
/*       */     //   731: iand
/*       */     //   732: isub
/*       */     //   733: if_icmpeq -> 739
/*       */     //   736: goto -> 754
/*       */     //   739: aload #6
/*       */     //   741: aload_0
/*       */     //   742: aload #6
/*       */     //   744: <illegal opcode> run : (Lme/levansj01/dc;Lme/levansj01/verus/compat/NMSManager;)Ljava/lang/Runnable;
/*       */     //   749: <illegal opcode> bbew : (Ljava/lang/Object;Ljava/lang/Runnable;)V
/*       */     //   754: aload_0
/*       */     //   755: getfield effects : Lme/levansj01/lh;
/*       */     //   758: <illegal opcode> bbex : (Ljava/lang/Object;)V
/*       */     //   763: aload_0
/*       */     //   764: getfield speedLevel : Lme/levansj01/lh;
/*       */     //   767: <illegal opcode> bbey : (Ljava/lang/Object;)V
/*       */     //   772: aload_0
/*       */     //   773: getfield slowLevel : Lme/levansj01/lh;
/*       */     //   776: <illegal opcode> bbez : (Ljava/lang/Object;)V
/*       */     //   781: aload_0
/*       */     //   782: getfield jumpLevel : Lme/levansj01/lh;
/*       */     //   785: <illegal opcode> bbfa : (Ljava/lang/Object;)V
/*       */     //   790: aload_0
/*       */     //   791: <illegal opcode> bbfb : (Ljava/lang/Object;)V
/*       */     //   796: aload_0
/*       */     //   797: getfield velocityQueue : Ljava/util/Queue;
/*       */     //   800: aload_0
/*       */     //   801: lload #4
/*       */     //   803: aload_1
/*       */     //   804: <illegal opcode> test : (Lme/levansj01/dc;JLme/levansj01/l;)Ljava/util/function/Predicate;
/*       */     //   809: <illegal opcode> bbfc : (Ljava/lang/Object;Ljava/util/function/Predicate;)Z
/*       */     //   814: pop
/*       */     //   815: aload_0
/*       */     //   816: aload_0
/*       */     //   817: getfield lastLocation2 : Lme/levansj01/nq;
/*       */     //   820: putfield lastLastLocation2 : Lme/levansj01/nq;
/*       */     //   823: aload_0
/*       */     //   824: aload_0
/*       */     //   825: getfield currentLocation2 : Lme/levansj01/nq;
/*       */     //   828: putfield lastLocation2 : Lme/levansj01/nq;
/*       */     //   831: aload_0
/*       */     //   832: aload_1
/*       */     //   833: aload_0
/*       */     //   834: getfield lastLocation2 : Lme/levansj01/nq;
/*       */     //   837: <illegal opcode> bbfd : (Ljava/lang/Object;Lme/levansj01/nq;)Lme/levansj01/nq;
/*       */     //   842: putfield currentLocation2 : Lme/levansj01/nq;
/*       */     //   845: aload_1
/*       */     //   846: <illegal opcode> bbfe : (Ljava/lang/Object;)Z
/*       */     //   851: ifne -> 857
/*       */     //   854: goto -> 890
/*       */     //   857: aload_0
/*       */     //   858: getfield currentLocation2 : Lme/levansj01/nq;
/*       */     //   861: aload_0
/*       */     //   862: getfield lastLocation2 : Lme/levansj01/nq;
/*       */     //   865: <illegal opcode> bbff : (Ljava/lang/Object;Lme/levansj01/ir;)D
/*       */     //   870: dconst_0
/*       */     //   871: dcmpl
/*       */     //   872: ifne -> 878
/*       */     //   875: goto -> 890
/*       */     //   878: aload_0
/*       */     //   879: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   882: getstatic me/levansj01/ij.NOT_MOVING : Lme/levansj01/ij;
/*       */     //   885: <illegal opcode> bbfg : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   890: aload_0
/*       */     //   891: getfield checkData : Lme/levansj01/fc;
/*       */     //   894: <illegal opcode> bbfh : (Ljava/lang/Object;)[Lme/levansj01/et;
/*       */     //   899: checkcast [Lme/levansj01/et;
/*       */     //   902: astore #7
/*       */     //   904: aload #7
/*       */     //   906: arraylength
/*       */     //   907: istore #8
/*       */     //   909: ldc2_w -5838500030180213522
/*       */     //   912: l2i
/*       */     //   913: sipush #15598
/*       */     //   916: istore #25
/*       */     //   918: istore #24
/*       */     //   920: iload #24
/*       */     //   922: iload #25
/*       */     //   924: ior
/*       */     //   925: iload #24
/*       */     //   927: iload #25
/*       */     //   929: iand
/*       */     //   930: iconst_m1
/*       */     //   931: ixor
/*       */     //   932: iand
/*       */     //   933: istore #9
/*       */     //   935: iload #9
/*       */     //   937: iload #8
/*       */     //   939: if_icmplt -> 945
/*       */     //   942: goto -> 975
/*       */     //   945: aload #7
/*       */     //   947: iload #9
/*       */     //   949: aaload
/*       */     //   950: astore #10
/*       */     //   952: aload #10
/*       */     //   954: aload_0
/*       */     //   955: getfield lastLocation2 : Lme/levansj01/nq;
/*       */     //   958: aload_0
/*       */     //   959: getfield currentLocation2 : Lme/levansj01/nq;
/*       */     //   962: lload #4
/*       */     //   964: <illegal opcode> bbfi : (Ljava/lang/Object;Lme/levansj01/nq;Lme/levansj01/nq;J)V
/*       */     //   969: iinc #9, 1
/*       */     //   972: goto -> 935
/*       */     //   975: aload_0
/*       */     //   976: getfield location : Lme/levansj01/gz;
/*       */     //   979: lload #4
/*       */     //   981: <illegal opcode> bbfj : (Ljava/lang/Object;J)V
/*       */     //   986: aload_0
/*       */     //   987: getfield location : Lme/levansj01/gz;
/*       */     //   990: aload_1
/*       */     //   991: <illegal opcode> bbfk : (Ljava/lang/Object;)Z
/*       */     //   996: <illegal opcode> bbfl : (Ljava/lang/Object;Z)V
/*       */     //   1001: aload_0
/*       */     //   1002: getfield location : Lme/levansj01/gz;
/*       */     //   1005: aload_1
/*       */     //   1006: <illegal opcode> bbfm : (Ljava/lang/Object;)Z
/*       */     //   1011: <illegal opcode> bbfn : (Z)Ljava/lang/Boolean;
/*       */     //   1016: <illegal opcode> bbfo : (Ljava/lang/Object;Ljava/lang/Boolean;)V
/*       */     //   1021: aload_1
/*       */     //   1022: <illegal opcode> bbfp : (Ljava/lang/Object;)Z
/*       */     //   1027: ifne -> 1033
/*       */     //   1030: goto -> 1078
/*       */     //   1033: aload_0
/*       */     //   1034: getfield location : Lme/levansj01/gz;
/*       */     //   1037: aload_1
/*       */     //   1038: <illegal opcode> bbfq : (Ljava/lang/Object;)D
/*       */     //   1043: <illegal opcode> bbfr : (Ljava/lang/Object;D)V
/*       */     //   1048: aload_0
/*       */     //   1049: getfield location : Lme/levansj01/gz;
/*       */     //   1052: aload_1
/*       */     //   1053: <illegal opcode> bbfs : (Ljava/lang/Object;)D
/*       */     //   1058: <illegal opcode> bbft : (Ljava/lang/Object;D)V
/*       */     //   1063: aload_0
/*       */     //   1064: getfield location : Lme/levansj01/gz;
/*       */     //   1067: aload_1
/*       */     //   1068: <illegal opcode> bbfu : (Ljava/lang/Object;)D
/*       */     //   1073: <illegal opcode> bbfv : (Ljava/lang/Object;D)V
/*       */     //   1078: aload_1
/*       */     //   1079: <illegal opcode> bbfw : (Ljava/lang/Object;)Z
/*       */     //   1084: ifne -> 1090
/*       */     //   1087: goto -> 1120
/*       */     //   1090: aload_0
/*       */     //   1091: getfield location : Lme/levansj01/gz;
/*       */     //   1094: aload_1
/*       */     //   1095: <illegal opcode> bbfx : (Ljava/lang/Object;)F
/*       */     //   1100: <illegal opcode> bbfy : (Ljava/lang/Object;F)V
/*       */     //   1105: aload_0
/*       */     //   1106: getfield location : Lme/levansj01/gz;
/*       */     //   1109: aload_1
/*       */     //   1110: <illegal opcode> bbfz : (Ljava/lang/Object;)F
/*       */     //   1115: <illegal opcode> bbga : (Ljava/lang/Object;F)V
/*       */     //   1120: aload_0
/*       */     //   1121: aload_0
/*       */     //   1122: dup
/*       */     //   1123: getfield nonMoveTicks : I
/*       */     //   1126: dup_x1
/*       */     //   1127: sipush #15729
/*       */     //   1130: sipush #15728
/*       */     //   1133: istore #25
/*       */     //   1135: istore #24
/*       */     //   1137: iload #24
/*       */     //   1139: iload #25
/*       */     //   1141: ior
/*       */     //   1142: iload #24
/*       */     //   1144: iload #25
/*       */     //   1146: iand
/*       */     //   1147: iconst_m1
/*       */     //   1148: ixor
/*       */     //   1149: iand
/*       */     //   1150: istore #25
/*       */     //   1152: istore #24
/*       */     //   1154: iconst_2
/*       */     //   1155: iload #24
/*       */     //   1157: iload #25
/*       */     //   1159: ior
/*       */     //   1160: imul
/*       */     //   1161: iload #24
/*       */     //   1163: iload #25
/*       */     //   1165: ixor
/*       */     //   1166: isub
/*       */     //   1167: putfield nonMoveTicks : I
/*       */     //   1170: putfield lastNonMoveTicks : I
/*       */     //   1173: aload_1
/*       */     //   1174: <illegal opcode> bbgb : (Ljava/lang/Object;)Z
/*       */     //   1179: ifne -> 1185
/*       */     //   1182: goto -> 1218
/*       */     //   1185: aload_0
/*       */     //   1186: sipush #9753
/*       */     //   1189: istore #23
/*       */     //   1191: iload #23
/*       */     //   1193: sipush #9753
/*       */     //   1196: istore #25
/*       */     //   1198: istore #24
/*       */     //   1200: iload #24
/*       */     //   1202: iload #25
/*       */     //   1204: iconst_m1
/*       */     //   1205: ixor
/*       */     //   1206: iand
/*       */     //   1207: iload #24
/*       */     //   1209: iconst_m1
/*       */     //   1210: ixor
/*       */     //   1211: iload #25
/*       */     //   1213: iand
/*       */     //   1214: ior
/*       */     //   1215: putfield nonMoveTicks : I
/*       */     //   1218: lload #4
/*       */     //   1220: aload_0
/*       */     //   1221: getfield lastFlying : J
/*       */     //   1224: lsub
/*       */     //   1225: lstore #7
/*       */     //   1227: lload #7
/*       */     //   1229: ldc2_w 110
/*       */     //   1232: lcmp
/*       */     //   1233: ifgt -> 1239
/*       */     //   1236: goto -> 1245
/*       */     //   1239: aload_0
/*       */     //   1240: lload #4
/*       */     //   1242: putfield lastDelayed : J
/*       */     //   1245: lload #7
/*       */     //   1247: ldc2_w 15
/*       */     //   1250: lcmp
/*       */     //   1251: iflt -> 1257
/*       */     //   1254: goto -> 1263
/*       */     //   1257: aload_0
/*       */     //   1258: lload #4
/*       */     //   1260: putfield lastFast : J
/*       */     //   1263: aload_0
/*       */     //   1264: aload_0
/*       */     //   1265: getfield lastFlying : J
/*       */     //   1268: putfield lastLastFlying : J
/*       */     //   1271: aload_0
/*       */     //   1272: lload #4
/*       */     //   1274: putfield lastFlying : J
/*       */     //   1277: aload_0
/*       */     //   1278: aload_0
/*       */     //   1279: getfield teleportList : Ljava/util/Queue;
/*       */     //   1282: <illegal opcode> bbgc : (Ljava/lang/Object;)Z
/*       */     //   1287: ifne -> 1293
/*       */     //   1290: goto -> 1378
/*       */     //   1293: aload_0
/*       */     //   1294: dup
/*       */     //   1295: getfield teleportTicks : I
/*       */     //   1298: sipush #8890
/*       */     //   1301: sipush #8891
/*       */     //   1304: istore #25
/*       */     //   1306: istore #24
/*       */     //   1308: iload #24
/*       */     //   1310: iload #25
/*       */     //   1312: ior
/*       */     //   1313: iload #24
/*       */     //   1315: iload #25
/*       */     //   1317: iand
/*       */     //   1318: isub
/*       */     //   1319: istore #25
/*       */     //   1321: istore #24
/*       */     //   1323: iload #24
/*       */     //   1325: iload #25
/*       */     //   1327: iconst_m1
/*       */     //   1328: ixor
/*       */     //   1329: isub
/*       */     //   1330: iconst_1
/*       */     //   1331: isub
/*       */     //   1332: dup_x1
/*       */     //   1333: putfield teleportTicks : I
/*       */     //   1336: getstatic me/levansj01/dc.dqx : Z
/*       */     //   1339: ifne -> 1345
/*       */     //   1342: goto -> 1404
/*       */     //   1345: ldc_w 2032413651
/*       */     //   1348: istore #23
/*       */     //   1350: ldc2_w -4300331151806946153
/*       */     //   1353: l2i
/*       */     //   1354: iload #23
/*       */     //   1356: istore #25
/*       */     //   1358: istore #24
/*       */     //   1360: iload #24
/*       */     //   1362: iload #25
/*       */     //   1364: ior
/*       */     //   1365: iload #24
/*       */     //   1367: iload #25
/*       */     //   1369: iand
/*       */     //   1370: iconst_m1
/*       */     //   1371: ixor
/*       */     //   1372: iand
/*       */     //   1373: istore #20
/*       */     //   1375: goto -> 1404
/*       */     //   1378: ldc2_w 8892873964125163293
/*       */     //   1381: l2i
/*       */     //   1382: sipush #3869
/*       */     //   1385: istore #25
/*       */     //   1387: istore #24
/*       */     //   1389: iload #24
/*       */     //   1391: iload #25
/*       */     //   1393: ior
/*       */     //   1394: iload #24
/*       */     //   1396: iconst_m1
/*       */     //   1397: ixor
/*       */     //   1398: iload #25
/*       */     //   1400: iconst_m1
/*       */     //   1401: ixor
/*       */     //   1402: ior
/*       */     //   1403: iand
/*       */     //   1404: putfield teleportTicks : I
/*       */     //   1407: aload_0
/*       */     //   1408: getfield version : Lme/levansj01/kb;
/*       */     //   1411: getstatic me/levansj01/kb.V1_9 : Lme/levansj01/kb;
/*       */     //   1414: if_acmpeq -> 1420
/*       */     //   1417: goto -> 1432
/*       */     //   1420: lload #7
/*       */     //   1422: ldc2_w 110
/*       */     //   1425: <illegal opcode> bbgd : (JJ)J
/*       */     //   1430: lstore #7
/*       */     //   1432: aload_0
/*       */     //   1433: getfield connectionFrequency : Lme/levansj01/mu;
/*       */     //   1436: sipush #32301
/*       */     //   1439: sipush #32287
/*       */     //   1442: istore #25
/*       */     //   1444: istore #24
/*       */     //   1446: iload #24
/*       */     //   1448: iload #25
/*       */     //   1450: ior
/*       */     //   1451: iload #24
/*       */     //   1453: iload #25
/*       */     //   1455: iand
/*       */     //   1456: isub
/*       */     //   1457: lload #7
/*       */     //   1459: l2i
/*       */     //   1460: istore #25
/*       */     //   1462: istore #24
/*       */     //   1464: iload #24
/*       */     //   1466: iload #25
/*       */     //   1468: iconst_m1
/*       */     //   1469: ixor
/*       */     //   1470: iand
/*       */     //   1471: iload #24
/*       */     //   1473: iconst_m1
/*       */     //   1474: ixor
/*       */     //   1475: iload #25
/*       */     //   1477: iand
/*       */     //   1478: isub
/*       */     //   1479: <illegal opcode> bbge : (I)Ljava/lang/Integer;
/*       */     //   1484: <illegal opcode> bbgf : (Ljava/lang/Object;Ljava/lang/Object;)V
/*       */     //   1489: aload_0
/*       */     //   1490: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   1493: getstatic me/levansj01/ij.POSSIBLE_SWINGS : Lme/levansj01/ij;
/*       */     //   1496: <illegal opcode> bbgg : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   1501: istore #9
/*       */     //   1503: iload #9
/*       */     //   1505: ifgt -> 1511
/*       */     //   1508: goto -> 1576
/*       */     //   1511: aload_0
/*       */     //   1512: getfield swingDigging : Z
/*       */     //   1515: ifeq -> 1521
/*       */     //   1518: goto -> 1576
/*       */     //   1521: aload_0
/*       */     //   1522: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   1525: getstatic me/levansj01/ij.LEGIT_SWING : Lme/levansj01/ij;
/*       */     //   1528: <illegal opcode> bbgh : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   1533: iload #9
/*       */     //   1535: bipush #92
/*       */     //   1537: istore #23
/*       */     //   1539: iload #23
/*       */     //   1541: bipush #93
/*       */     //   1543: istore #25
/*       */     //   1545: istore #24
/*       */     //   1547: iload #24
/*       */     //   1549: iload #25
/*       */     //   1551: ior
/*       */     //   1552: iload #24
/*       */     //   1554: iload #25
/*       */     //   1556: iand
/*       */     //   1557: isub
/*       */     //   1558: if_icmpgt -> 1564
/*       */     //   1561: goto -> 1576
/*       */     //   1564: aload_0
/*       */     //   1565: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   1568: getstatic me/levansj01/ij.DOUBLE_SWING : Lme/levansj01/ij;
/*       */     //   1571: <illegal opcode> bbgi : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   1576: aload_0
/*       */     //   1577: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   1580: getstatic me/levansj01/ij.POSSIBLE_SWINGS : Lme/levansj01/ij;
/*       */     //   1583: <illegal opcode> bbgj : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   1588: aload_0
/*       */     //   1589: getfield placing : Z
/*       */     //   1592: ifne -> 1598
/*       */     //   1595: goto -> 1628
/*       */     //   1598: aload_0
/*       */     //   1599: ldc2_w 1457043135199729169
/*       */     //   1602: l2i
/*       */     //   1603: sipush #22033
/*       */     //   1606: istore #25
/*       */     //   1608: istore #24
/*       */     //   1610: iload #24
/*       */     //   1612: iload #25
/*       */     //   1614: ior
/*       */     //   1615: iload #24
/*       */     //   1617: iconst_m1
/*       */     //   1618: ixor
/*       */     //   1619: iload #25
/*       */     //   1621: iconst_m1
/*       */     //   1622: ixor
/*       */     //   1623: ior
/*       */     //   1624: iand
/*       */     //   1625: putfield placing : Z
/*       */     //   1628: aload_0
/*       */     //   1629: getfield abortedDigging : Z
/*       */     //   1632: ifne -> 1638
/*       */     //   1635: goto -> 1726
/*       */     //   1638: aload_0
/*       */     //   1639: ldc2_w 8117307169453395388
/*       */     //   1642: l2i
/*       */     //   1643: sipush #19900
/*       */     //   1646: istore #25
/*       */     //   1648: istore #24
/*       */     //   1650: iload #24
/*       */     //   1652: iload #25
/*       */     //   1654: iconst_m1
/*       */     //   1655: ixor
/*       */     //   1656: iand
/*       */     //   1657: iload #24
/*       */     //   1659: iconst_m1
/*       */     //   1660: ixor
/*       */     //   1661: iload #25
/*       */     //   1663: iand
/*       */     //   1664: ior
/*       */     //   1665: putfield abortedDigging : Z
/*       */     //   1668: aload_0
/*       */     //   1669: sipush #14890
/*       */     //   1672: ldc2_w 8667688459428706858
/*       */     //   1675: l2i
/*       */     //   1676: istore #25
/*       */     //   1678: istore #24
/*       */     //   1680: iload #24
/*       */     //   1682: iload #25
/*       */     //   1684: iconst_m1
/*       */     //   1685: ixor
/*       */     //   1686: iand
/*       */     //   1687: iload #24
/*       */     //   1689: iconst_m1
/*       */     //   1690: ixor
/*       */     //   1691: iload #25
/*       */     //   1693: iand
/*       */     //   1694: ior
/*       */     //   1695: putfield swingDigging : Z
/*       */     //   1698: aload_0
/*       */     //   1699: sipush #14491
/*       */     //   1702: ldc2_w -7842751771603617637
/*       */     //   1705: l2i
/*       */     //   1706: istore #25
/*       */     //   1708: istore #24
/*       */     //   1710: iload #24
/*       */     //   1712: iload #25
/*       */     //   1714: ior
/*       */     //   1715: iload #24
/*       */     //   1717: iload #25
/*       */     //   1719: iand
/*       */     //   1720: iconst_m1
/*       */     //   1721: ixor
/*       */     //   1722: iand
/*       */     //   1723: putfield digging : Z
/*       */     //   1726: aload_0
/*       */     //   1727: getfield stoppedDigging : Z
/*       */     //   1730: ifne -> 1736
/*       */     //   1733: goto -> 1794
/*       */     //   1736: aload_0
/*       */     //   1737: ldc2_w -4852035669666531319
/*       */     //   1740: l2i
/*       */     //   1741: sipush #30729
/*       */     //   1744: istore #25
/*       */     //   1746: istore #24
/*       */     //   1748: iload #24
/*       */     //   1750: iload #25
/*       */     //   1752: ior
/*       */     //   1753: iload #24
/*       */     //   1755: iconst_m1
/*       */     //   1756: ixor
/*       */     //   1757: iload #25
/*       */     //   1759: iconst_m1
/*       */     //   1760: ixor
/*       */     //   1761: ior
/*       */     //   1762: iand
/*       */     //   1763: putfield stoppedDigging : Z
/*       */     //   1766: aload_0
/*       */     //   1767: ldc2_w 8570693426276754209
/*       */     //   1770: l2i
/*       */     //   1771: sipush #25377
/*       */     //   1774: istore #25
/*       */     //   1776: istore #24
/*       */     //   1778: iload #24
/*       */     //   1780: iload #25
/*       */     //   1782: ior
/*       */     //   1783: iload #24
/*       */     //   1785: iload #25
/*       */     //   1787: iand
/*       */     //   1788: iconst_m1
/*       */     //   1789: ixor
/*       */     //   1790: iand
/*       */     //   1791: putfield digging : Z
/*       */     //   1794: aload_0
/*       */     //   1795: getfield resetVelocity : Z
/*       */     //   1798: ifne -> 1804
/*       */     //   1801: goto -> 1839
/*       */     //   1804: aload_0
/*       */     //   1805: dconst_0
/*       */     //   1806: putfield lastVelY : D
/*       */     //   1809: aload_0
/*       */     //   1810: ldc2_w -7541115810383632798
/*       */     //   1813: l2i
/*       */     //   1814: sipush #26210
/*       */     //   1817: istore #25
/*       */     //   1819: istore #24
/*       */     //   1821: iload #24
/*       */     //   1823: iload #25
/*       */     //   1825: ior
/*       */     //   1826: iload #24
/*       */     //   1828: iconst_m1
/*       */     //   1829: ixor
/*       */     //   1830: iload #25
/*       */     //   1832: iconst_m1
/*       */     //   1833: ixor
/*       */     //   1834: ior
/*       */     //   1835: iand
/*       */     //   1836: putfield resetVelocity : Z
/*       */     //   1839: aload_0
/*       */     //   1840: getfield teleportList : Ljava/util/Queue;
/*       */     //   1843: <illegal opcode> bbgk : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   1848: checkcast me/levansj01/gz
/*       */     //   1851: dup
/*       */     //   1852: astore_3
/*       */     //   1853: ifnull -> 2141
/*       */     //   1856: aload_0
/*       */     //   1857: getfield totalTicks : I
/*       */     //   1860: aload_3
/*       */     //   1861: <illegal opcode> bbgl : (Ljava/lang/Object;)I
/*       */     //   1866: istore #25
/*       */     //   1868: istore #24
/*       */     //   1870: iload #24
/*       */     //   1872: iload #25
/*       */     //   1874: iconst_m1
/*       */     //   1875: ixor
/*       */     //   1876: iadd
/*       */     //   1877: iconst_1
/*       */     //   1878: iadd
/*       */     //   1879: istore #10
/*       */     //   1881: aload_1
/*       */     //   1882: <illegal opcode> bbgm : (Ljava/lang/Object;)Z
/*       */     //   1887: ifne -> 1893
/*       */     //   1890: goto -> 2044
/*       */     //   1893: iload #10
/*       */     //   1895: aload_0
/*       */     //   1896: <illegal opcode> bbgn : (Ljava/lang/Object;)I
/*       */     //   1901: if_icmpge -> 1907
/*       */     //   1904: goto -> 2044
/*       */     //   1907: aload_3
/*       */     //   1908: aload_0
/*       */     //   1909: getfield location : Lme/levansj01/gz;
/*       */     //   1912: <illegal opcode> bbgo : (Ljava/lang/Object;Lme/levansj01/ir;)Z
/*       */     //   1917: ifne -> 1923
/*       */     //   1920: goto -> 2044
/*       */     //   1923: aload_0
/*       */     //   1924: getfield teleportList : Ljava/util/Queue;
/*       */     //   1927: <illegal opcode> bbgp : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   1932: pop
/*       */     //   1933: aload_0
/*       */     //   1934: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   1937: aload_0
/*       */     //   1938: getfield location : Lme/levansj01/gz;
/*       */     //   1941: <illegal opcode> bbgq : (Ljava/lang/Object;Lme/levansj01/nr;)D
/*       */     //   1946: ldc2_w 4.0
/*       */     //   1949: dcmpl
/*       */     //   1950: ifgt -> 1956
/*       */     //   1953: goto -> 1986
/*       */     //   1956: aload_0
/*       */     //   1957: ldc2_w 3582492467099868092
/*       */     //   1960: l2i
/*       */     //   1961: sipush #7100
/*       */     //   1964: istore #25
/*       */     //   1966: istore #24
/*       */     //   1968: iload #24
/*       */     //   1970: iload #25
/*       */     //   1972: iconst_m1
/*       */     //   1973: ixor
/*       */     //   1974: iand
/*       */     //   1975: iload #24
/*       */     //   1977: iconst_m1
/*       */     //   1978: ixor
/*       */     //   1979: iload #25
/*       */     //   1981: iand
/*       */     //   1982: ior
/*       */     //   1983: putfield spawned : I
/*       */     //   1986: aload_0
/*       */     //   1987: aload_0
/*       */     //   1988: aload_0
/*       */     //   1989: getfield velY : D
/*       */     //   1992: dup2_x1
/*       */     //   1993: putfield lastVelZ : D
/*       */     //   1996: putfield lastVelY : D
/*       */     //   1999: aload_0
/*       */     //   2000: aload_0
/*       */     //   2001: getfield lastVelZ : D
/*       */     //   2004: putfield lastVelX : D
/*       */     //   2007: aload_0
/*       */     //   2008: aload_0
/*       */     //   2009: getfield location : Lme/levansj01/gz;
/*       */     //   2012: <illegal opcode> bbgr : (Ljava/lang/Object;)Lme/levansj01/gz;
/*       */     //   2017: putfield lastLocation : Lme/levansj01/gz;
/*       */     //   2020: aload_0
/*       */     //   2021: aload_0
/*       */     //   2022: getfield location : Lme/levansj01/gz;
/*       */     //   2025: <illegal opcode> bbgs : (Ljava/lang/Object;)Lme/levansj01/gz;
/*       */     //   2030: putfield lastLastLocation : Lme/levansj01/gz;
/*       */     //   2033: aload_0
/*       */     //   2034: aload_0
/*       */     //   2035: getfield totalTicks : I
/*       */     //   2038: putfield lastTeleportTicks : I
/*       */     //   2041: goto -> 2141
/*       */     //   2044: iload #10
/*       */     //   2046: aload_1
/*       */     //   2047: <illegal opcode> bbgt : (Ljava/lang/Object;)Z
/*       */     //   2052: ifne -> 2058
/*       */     //   2055: goto -> 2090
/*       */     //   2058: aload_0
/*       */     //   2059: <illegal opcode> bbgu : (Ljava/lang/Object;)I
/*       */     //   2064: ldc2_w 7410593038453441524
/*       */     //   2067: l2i
/*       */     //   2068: sipush #1014
/*       */     //   2071: istore #25
/*       */     //   2073: istore #24
/*       */     //   2075: iload #24
/*       */     //   2077: iload #25
/*       */     //   2079: ior
/*       */     //   2080: iload #24
/*       */     //   2082: iload #25
/*       */     //   2084: iand
/*       */     //   2085: isub
/*       */     //   2086: imul
/*       */     //   2087: goto -> 2125
/*       */     //   2090: aload_0
/*       */     //   2091: <illegal opcode> bbgv : (Ljava/lang/Object;)I
/*       */     //   2096: sipush #8047
/*       */     //   2099: istore #23
/*       */     //   2101: ldc2_w -964362871125303445
/*       */     //   2104: l2i
/*       */     //   2105: iload #23
/*       */     //   2107: istore #25
/*       */     //   2109: istore #24
/*       */     //   2111: iload #24
/*       */     //   2113: iload #25
/*       */     //   2115: ior
/*       */     //   2116: iload #24
/*       */     //   2118: iload #25
/*       */     //   2120: iand
/*       */     //   2121: iconst_m1
/*       */     //   2122: ixor
/*       */     //   2123: iand
/*       */     //   2124: imul
/*       */     //   2125: if_icmpgt -> 2131
/*       */     //   2128: goto -> 2141
/*       */     //   2131: aload_0
/*       */     //   2132: getfield teleportList : Ljava/util/Queue;
/*       */     //   2135: <illegal opcode> bbgw : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   2140: pop
/*       */     //   2141: aload_0
/*       */     //   2142: getfield lastVelY : D
/*       */     //   2145: dconst_0
/*       */     //   2146: dcmpl
/*       */     //   2147: ifeq -> 2153
/*       */     //   2150: goto -> 2209
/*       */     //   2153: aload_0
/*       */     //   2154: getfield velY : D
/*       */     //   2157: dconst_0
/*       */     //   2158: dcmpl
/*       */     //   2159: ifne -> 2165
/*       */     //   2162: goto -> 2209
/*       */     //   2165: aload_1
/*       */     //   2166: <illegal opcode> bbgx : (Ljava/lang/Object;)Z
/*       */     //   2171: ifne -> 2177
/*       */     //   2174: goto -> 2185
/*       */     //   2177: aload_0
/*       */     //   2178: dconst_0
/*       */     //   2179: putfield velY : D
/*       */     //   2182: goto -> 2209
/*       */     //   2185: aload_0
/*       */     //   2186: dup
/*       */     //   2187: getfield velY : D
/*       */     //   2190: ldc2_w 0.08
/*       */     //   2193: dsub
/*       */     //   2194: putfield velY : D
/*       */     //   2197: aload_0
/*       */     //   2198: dup
/*       */     //   2199: getfield velY : D
/*       */     //   2202: ldc2_w 0.98
/*       */     //   2205: dmul
/*       */     //   2206: putfield velY : D
/*       */     //   2209: aload_1
/*       */     //   2210: <illegal opcode> bbgy : (Ljava/lang/Object;)Z
/*       */     //   2215: ifne -> 2221
/*       */     //   2218: goto -> 2350
/*       */     //   2221: aload_0
/*       */     //   2222: getfield fallFlying : Z
/*       */     //   2225: ifne -> 2231
/*       */     //   2228: goto -> 2306
/*       */     //   2231: aload_0
/*       */     //   2232: sipush #28296
/*       */     //   2235: istore #23
/*       */     //   2237: iload #23
/*       */     //   2239: sipush #28296
/*       */     //   2242: istore #25
/*       */     //   2244: istore #24
/*       */     //   2246: iload #24
/*       */     //   2248: iload #25
/*       */     //   2250: ior
/*       */     //   2251: iload #24
/*       */     //   2253: iconst_m1
/*       */     //   2254: ixor
/*       */     //   2255: iload #25
/*       */     //   2257: iconst_m1
/*       */     //   2258: ixor
/*       */     //   2259: ior
/*       */     //   2260: iand
/*       */     //   2261: putfield fallFlying : Z
/*       */     //   2264: aload_0
/*       */     //   2265: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   2268: getstatic me/levansj01/ij.ELYTRA_EXIT : Lme/levansj01/ij;
/*       */     //   2271: sipush #-8082
/*       */     //   2274: istore #23
/*       */     //   2276: ldc2_w -6534822724030685198
/*       */     //   2279: l2i
/*       */     //   2280: iload #23
/*       */     //   2282: istore #25
/*       */     //   2284: istore #24
/*       */     //   2286: iload #24
/*       */     //   2288: iload #25
/*       */     //   2290: iconst_m1
/*       */     //   2291: ixor
/*       */     //   2292: iand
/*       */     //   2293: iload #24
/*       */     //   2295: iconst_m1
/*       */     //   2296: ixor
/*       */     //   2297: iload #25
/*       */     //   2299: iand
/*       */     //   2300: ior
/*       */     //   2301: <illegal opcode> bbgz : (Ljava/lang/Object;Lme/levansj01/ij;I)V
/*       */     //   2306: aload_0
/*       */     //   2307: <illegal opcode> bbha : (Ljava/lang/Object;)Z
/*       */     //   2312: ifeq -> 2318
/*       */     //   2315: goto -> 2350
/*       */     //   2318: aload_0
/*       */     //   2319: ldc_w 2147477488
/*       */     //   2322: istore #23
/*       */     //   2324: ldc2_w -2416159085297657841
/*       */     //   2327: l2i
/*       */     //   2328: iload #23
/*       */     //   2330: istore #25
/*       */     //   2332: istore #24
/*       */     //   2334: iload #24
/*       */     //   2336: iload #25
/*       */     //   2338: ior
/*       */     //   2339: iload #24
/*       */     //   2341: iload #25
/*       */     //   2343: iand
/*       */     //   2344: iconst_m1
/*       */     //   2345: ixor
/*       */     //   2346: iand
/*       */     //   2347: putfield spawned : I
/*       */     //   2350: aload_0
/*       */     //   2351: getfield spawned : I
/*       */     //   2354: sipush #11161
/*       */     //   2357: istore #23
/*       */     //   2359: ldc2_w -3004671417049404991
/*       */     //   2362: l2i
/*       */     //   2363: iload #23
/*       */     //   2365: istore #25
/*       */     //   2367: istore #24
/*       */     //   2369: iload #24
/*       */     //   2371: iload #25
/*       */     //   2373: ior
/*       */     //   2374: iload #24
/*       */     //   2376: iconst_m1
/*       */     //   2377: ixor
/*       */     //   2378: iload #25
/*       */     //   2380: iconst_m1
/*       */     //   2381: ixor
/*       */     //   2382: ior
/*       */     //   2383: iand
/*       */     //   2384: if_icmple -> 2390
/*       */     //   2387: goto -> 2620
/*       */     //   2390: aload_0
/*       */     //   2391: dup
/*       */     //   2392: getfield spawned : I
/*       */     //   2395: sipush #9520
/*       */     //   2398: sipush #9521
/*       */     //   2401: istore #25
/*       */     //   2403: istore #24
/*       */     //   2405: iload #24
/*       */     //   2407: iload #25
/*       */     //   2409: ior
/*       */     //   2410: iload #24
/*       */     //   2412: iload #25
/*       */     //   2414: iand
/*       */     //   2415: iconst_m1
/*       */     //   2416: ixor
/*       */     //   2417: iand
/*       */     //   2418: istore #25
/*       */     //   2420: istore #24
/*       */     //   2422: iload #24
/*       */     //   2424: iload #25
/*       */     //   2426: ior
/*       */     //   2427: iload #24
/*       */     //   2429: iload #25
/*       */     //   2431: iand
/*       */     //   2432: iadd
/*       */     //   2433: putfield spawned : I
/*       */     //   2436: aload_1
/*       */     //   2437: <illegal opcode> bbhb : (Ljava/lang/Object;)Z
/*       */     //   2442: ifne -> 2448
/*       */     //   2445: goto -> 2620
/*       */     //   2448: aload_0
/*       */     //   2449: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   2452: <illegal opcode> bbhc : (Ljava/lang/Object;)D
/*       */     //   2457: aload_0
/*       */     //   2458: getfield location : Lme/levansj01/gz;
/*       */     //   2461: <illegal opcode> bbhd : (Ljava/lang/Object;)D
/*       */     //   2466: dcmpg
/*       */     //   2467: iflt -> 2473
/*       */     //   2470: goto -> 2523
/*       */     //   2473: aload_0
/*       */     //   2474: dup
/*       */     //   2475: getfield spawned : I
/*       */     //   2478: sipush #15726
/*       */     //   2481: ldc2_w -9078596549096751802
/*       */     //   2484: l2i
/*       */     //   2485: istore #25
/*       */     //   2487: istore #24
/*       */     //   2489: iload #24
/*       */     //   2491: iload #25
/*       */     //   2493: ior
/*       */     //   2494: iload #24
/*       */     //   2496: iload #25
/*       */     //   2498: iand
/*       */     //   2499: isub
/*       */     //   2500: istore #25
/*       */     //   2502: istore #24
/*       */     //   2504: iconst_2
/*       */     //   2505: iload #24
/*       */     //   2507: iload #25
/*       */     //   2509: ior
/*       */     //   2510: imul
/*       */     //   2511: iload #24
/*       */     //   2513: iload #25
/*       */     //   2515: ixor
/*       */     //   2516: isub
/*       */     //   2517: putfield spawned : I
/*       */     //   2520: goto -> 2620
/*       */     //   2523: aload_0
/*       */     //   2524: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   2527: <illegal opcode> bbhe : (Ljava/lang/Object;)D
/*       */     //   2532: aload_0
/*       */     //   2533: getfield location : Lme/levansj01/gz;
/*       */     //   2536: <illegal opcode> bbhf : (Ljava/lang/Object;)D
/*       */     //   2541: dcmpl
/*       */     //   2542: ifeq -> 2548
/*       */     //   2545: goto -> 2620
/*       */     //   2548: aload_0
/*       */     //   2549: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   2552: aload_0
/*       */     //   2553: getfield location : Lme/levansj01/gz;
/*       */     //   2556: <illegal opcode> bbhg : (Ljava/lang/Object;Lme/levansj01/ir;)Z
/*       */     //   2561: ifeq -> 2567
/*       */     //   2564: goto -> 2620
/*       */     //   2567: aload_0
/*       */     //   2568: dup
/*       */     //   2569: getfield spawned : I
/*       */     //   2572: sipush #8493
/*       */     //   2575: istore #23
/*       */     //   2577: iload #23
/*       */     //   2579: ldc2_w 3906140655402885415
/*       */     //   2582: l2i
/*       */     //   2583: istore #25
/*       */     //   2585: istore #24
/*       */     //   2587: iload #24
/*       */     //   2589: iload #25
/*       */     //   2591: ior
/*       */     //   2592: iload #24
/*       */     //   2594: iload #25
/*       */     //   2596: iand
/*       */     //   2597: iconst_m1
/*       */     //   2598: ixor
/*       */     //   2599: iand
/*       */     //   2600: istore #25
/*       */     //   2602: istore #24
/*       */     //   2604: iload #24
/*       */     //   2606: iload #25
/*       */     //   2608: ixor
/*       */     //   2609: iconst_2
/*       */     //   2610: iload #24
/*       */     //   2612: iload #25
/*       */     //   2614: iand
/*       */     //   2615: imul
/*       */     //   2616: iadd
/*       */     //   2617: putfield spawned : I
/*       */     //   2620: aload_0
/*       */     //   2621: aload_0
/*       */     //   2622: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   2625: <illegal opcode> bbhh : (Ljava/lang/Object;)Z
/*       */     //   2630: ifne -> 2636
/*       */     //   2633: goto -> 2660
/*       */     //   2636: sipush #6974
/*       */     //   2639: sipush #6974
/*       */     //   2642: istore #25
/*       */     //   2644: istore #24
/*       */     //   2646: iload #24
/*       */     //   2648: iload #25
/*       */     //   2650: ior
/*       */     //   2651: iload #24
/*       */     //   2653: iload #25
/*       */     //   2655: iand
/*       */     //   2656: isub
/*       */     //   2657: goto -> 2711
/*       */     //   2660: aload_0
/*       */     //   2661: dup
/*       */     //   2662: getfield flyingTicks : I
/*       */     //   2665: sipush #9405
/*       */     //   2668: istore #23
/*       */     //   2670: iload #23
/*       */     //   2672: sipush #9404
/*       */     //   2675: istore #25
/*       */     //   2677: istore #24
/*       */     //   2679: iload #24
/*       */     //   2681: iload #25
/*       */     //   2683: iconst_m1
/*       */     //   2684: ixor
/*       */     //   2685: iand
/*       */     //   2686: iload #24
/*       */     //   2688: iconst_m1
/*       */     //   2689: ixor
/*       */     //   2690: iload #25
/*       */     //   2692: iand
/*       */     //   2693: ior
/*       */     //   2694: istore #25
/*       */     //   2696: istore #24
/*       */     //   2698: iload #24
/*       */     //   2700: iload #25
/*       */     //   2702: iconst_m1
/*       */     //   2703: ixor
/*       */     //   2704: isub
/*       */     //   2705: iconst_1
/*       */     //   2706: isub
/*       */     //   2707: dup_x1
/*       */     //   2708: putfield flyingTicks : I
/*       */     //   2711: putfield flyingTicks : I
/*       */     //   2714: aload_0
/*       */     //   2715: aload_0
/*       */     //   2716: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   2719: <illegal opcode> bbhi : (Ljava/lang/Object;)Z
/*       */     //   2724: ifne -> 2730
/*       */     //   2727: goto -> 2756
/*       */     //   2730: sipush #27971
/*       */     //   2733: sipush #27971
/*       */     //   2736: istore #25
/*       */     //   2738: istore #24
/*       */     //   2740: iload #24
/*       */     //   2742: iload #25
/*       */     //   2744: ior
/*       */     //   2745: iload #24
/*       */     //   2747: iload #25
/*       */     //   2749: iand
/*       */     //   2750: iconst_m1
/*       */     //   2751: ixor
/*       */     //   2752: iand
/*       */     //   2753: goto -> 2809
/*       */     //   2756: aload_0
/*       */     //   2757: dup
/*       */     //   2758: getfield allowFlightTicks : I
/*       */     //   2761: sipush #24692
/*       */     //   2764: istore #23
/*       */     //   2766: iload #23
/*       */     //   2768: sipush #24693
/*       */     //   2771: istore #25
/*       */     //   2773: istore #24
/*       */     //   2775: iload #24
/*       */     //   2777: iload #25
/*       */     //   2779: iconst_m1
/*       */     //   2780: ixor
/*       */     //   2781: iand
/*       */     //   2782: iload #24
/*       */     //   2784: iconst_m1
/*       */     //   2785: ixor
/*       */     //   2786: iload #25
/*       */     //   2788: iand
/*       */     //   2789: ior
/*       */     //   2790: istore #25
/*       */     //   2792: istore #24
/*       */     //   2794: iload #24
/*       */     //   2796: iload #25
/*       */     //   2798: ior
/*       */     //   2799: iload #24
/*       */     //   2801: iload #25
/*       */     //   2803: iand
/*       */     //   2804: iadd
/*       */     //   2805: dup_x1
/*       */     //   2806: putfield allowFlightTicks : I
/*       */     //   2809: putfield allowFlightTicks : I
/*       */     //   2812: aload_0
/*       */     //   2813: getfield wasVehicle : Z
/*       */     //   2816: ifne -> 2822
/*       */     //   2819: goto -> 2837
/*       */     //   2822: aload_0
/*       */     //   2823: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   2826: getstatic me/levansj01/ij.VEHICLE : Lme/levansj01/ij;
/*       */     //   2829: <illegal opcode> bbhj : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   2834: goto -> 2850
/*       */     //   2837: aload_0
/*       */     //   2838: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   2841: getstatic me/levansj01/ij.VEHICLE : Lme/levansj01/ij;
/*       */     //   2844: <illegal opcode> bbhk : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   2849: pop
/*       */     //   2850: aload_0
/*       */     //   2851: getfield vehicle : Z
/*       */     //   2854: ifeq -> 2860
/*       */     //   2857: goto -> 2872
/*       */     //   2860: aload_0
/*       */     //   2861: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   2864: <illegal opcode> bbhl : (Ljava/lang/Object;)Lorg/bukkit/entity/Entity;
/*       */     //   2869: ifnull -> 2916
/*       */     //   2872: aload_0
/*       */     //   2873: sipush #27840
/*       */     //   2876: istore #23
/*       */     //   2878: iload #23
/*       */     //   2880: sipush #27841
/*       */     //   2883: istore #25
/*       */     //   2885: istore #24
/*       */     //   2887: iload #24
/*       */     //   2889: iload #25
/*       */     //   2891: ior
/*       */     //   2892: iload #24
/*       */     //   2894: iload #25
/*       */     //   2896: iand
/*       */     //   2897: isub
/*       */     //   2898: putfield wasVehicle : Z
/*       */     //   2901: aload_0
/*       */     //   2902: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   2905: getstatic me/levansj01/ij.VEHICLE : Lme/levansj01/ij;
/*       */     //   2908: <illegal opcode> bbhm : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   2913: goto -> 2965
/*       */     //   2916: aload_0
/*       */     //   2917: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   2920: aload_0
/*       */     //   2921: getfield location : Lme/levansj01/gz;
/*       */     //   2924: <illegal opcode> bbhn : (Ljava/lang/Object;Lme/levansj01/ir;)Z
/*       */     //   2929: ifeq -> 2935
/*       */     //   2932: goto -> 2965
/*       */     //   2935: aload_0
/*       */     //   2936: sipush #1760
/*       */     //   2939: ldc2_w 3413890562367948512
/*       */     //   2942: l2i
/*       */     //   2943: istore #25
/*       */     //   2945: istore #24
/*       */     //   2947: iload #24
/*       */     //   2949: iload #25
/*       */     //   2951: iconst_m1
/*       */     //   2952: ixor
/*       */     //   2953: iand
/*       */     //   2954: iload #24
/*       */     //   2956: iconst_m1
/*       */     //   2957: ixor
/*       */     //   2958: iload #25
/*       */     //   2960: iand
/*       */     //   2961: ior
/*       */     //   2962: putfield wasVehicle : Z
/*       */     //   2965: aload_0
/*       */     //   2966: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   2969: <illegal opcode> bbho : (Ljava/lang/Object;)Lorg/bukkit/GameMode;
/*       */     //   2974: astore #10
/*       */     //   2976: aload_0
/*       */     //   2977: aload #10
/*       */     //   2979: getstatic org/bukkit/GameMode.SURVIVAL : Lorg/bukkit/GameMode;
/*       */     //   2982: if_acmpne -> 2988
/*       */     //   2985: goto -> 2999
/*       */     //   2988: aload #10
/*       */     //   2990: getstatic org/bukkit/GameMode.ADVENTURE : Lorg/bukkit/GameMode;
/*       */     //   2993: if_acmpeq -> 2999
/*       */     //   2996: goto -> 3045
/*       */     //   2999: aload_0
/*       */     //   3000: dup
/*       */     //   3001: getfield survivalTicks : I
/*       */     //   3004: sipush #1171
/*       */     //   3007: sipush #1170
/*       */     //   3010: istore #25
/*       */     //   3012: istore #24
/*       */     //   3014: iload #24
/*       */     //   3016: iload #25
/*       */     //   3018: ior
/*       */     //   3019: iload #24
/*       */     //   3021: iload #25
/*       */     //   3023: iand
/*       */     //   3024: isub
/*       */     //   3025: istore #25
/*       */     //   3027: istore #24
/*       */     //   3029: iload #24
/*       */     //   3031: iload #25
/*       */     //   3033: iconst_m1
/*       */     //   3034: ixor
/*       */     //   3035: isub
/*       */     //   3036: iconst_1
/*       */     //   3037: isub
/*       */     //   3038: dup_x1
/*       */     //   3039: putfield survivalTicks : I
/*       */     //   3042: goto -> 3071
/*       */     //   3045: sipush #29606
/*       */     //   3048: ldc2_w 3408648382984516518
/*       */     //   3051: l2i
/*       */     //   3052: istore #25
/*       */     //   3054: istore #24
/*       */     //   3056: iload #24
/*       */     //   3058: iload #25
/*       */     //   3060: iconst_m1
/*       */     //   3061: ixor
/*       */     //   3062: iand
/*       */     //   3063: iload #24
/*       */     //   3065: iconst_m1
/*       */     //   3066: ixor
/*       */     //   3067: iload #25
/*       */     //   3069: iand
/*       */     //   3070: ior
/*       */     //   3071: putfield survivalTicks : I
/*       */     //   3074: aload #10
/*       */     //   3076: <illegal opcode> bbhp : (Ljava/lang/Object;)I
/*       */     //   3081: ldc2_w -3508068092678747117
/*       */     //   3084: l2i
/*       */     //   3085: sipush #19472
/*       */     //   3088: istore #25
/*       */     //   3090: istore #24
/*       */     //   3092: iload #24
/*       */     //   3094: iload #25
/*       */     //   3096: iconst_m1
/*       */     //   3097: ixor
/*       */     //   3098: iand
/*       */     //   3099: iload #24
/*       */     //   3101: iconst_m1
/*       */     //   3102: ixor
/*       */     //   3103: iload #25
/*       */     //   3105: iand
/*       */     //   3106: ior
/*       */     //   3107: if_icmpeq -> 3113
/*       */     //   3110: goto -> 3145
/*       */     //   3113: aload_0
/*       */     //   3114: sipush #14087
/*       */     //   3117: sipush #14087
/*       */     //   3120: istore #25
/*       */     //   3122: istore #24
/*       */     //   3124: iload #24
/*       */     //   3126: iload #25
/*       */     //   3128: ior
/*       */     //   3129: iload #24
/*       */     //   3131: iload #25
/*       */     //   3133: iand
/*       */     //   3134: iconst_m1
/*       */     //   3135: ixor
/*       */     //   3136: iand
/*       */     //   3137: <illegal opcode> bbhq : (Z)Ljava/lang/Boolean;
/*       */     //   3142: putfield sprinting : Ljava/lang/Boolean;
/*       */     //   3145: aload_0
/*       */     //   3146: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   3149: <illegal opcode> bbhr : (Ljava/lang/Object;)Lorg/bukkit/inventory/ItemStack;
/*       */     //   3154: dup
/*       */     //   3155: astore_2
/*       */     //   3156: ifnull -> 3248
/*       */     //   3159: aload_2
/*       */     //   3160: <illegal opcode> bbhs : (Lorg/bukkit/inventory/ItemStack;)Z
/*       */     //   3165: ifne -> 3171
/*       */     //   3168: goto -> 3214
/*       */     //   3171: aload_2
/*       */     //   3172: <illegal opcode> bbht : (Ljava/lang/Object;)Lorg/bukkit/Material;
/*       */     //   3177: getstatic me/levansj01/if.BOW : Lorg/bukkit/Material;
/*       */     //   3180: <illegal opcode> bbhu : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   3185: ifne -> 3191
/*       */     //   3188: goto -> 3248
/*       */     //   3191: aload_0
/*       */     //   3192: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   3195: <illegal opcode> bbhv : (Ljava/lang/Object;)Lorg/bukkit/inventory/PlayerInventory;
/*       */     //   3200: getstatic me/levansj01/if.ARROW : Lorg/bukkit/Material;
/*       */     //   3203: <illegal opcode> bbhw : (Ljava/lang/Object;Lorg/bukkit/Material;)Z
/*       */     //   3208: ifeq -> 3214
/*       */     //   3211: goto -> 3248
/*       */     //   3214: aload_0
/*       */     //   3215: sipush #16261
/*       */     //   3218: istore #23
/*       */     //   3220: iload #23
/*       */     //   3222: ldc2_w -8269868393536929915
/*       */     //   3225: l2i
/*       */     //   3226: istore #25
/*       */     //   3228: istore #24
/*       */     //   3230: iload #24
/*       */     //   3232: iload #25
/*       */     //   3234: iconst_m1
/*       */     //   3235: ixor
/*       */     //   3236: iand
/*       */     //   3237: iload #24
/*       */     //   3239: iconst_m1
/*       */     //   3240: ixor
/*       */     //   3241: iload #25
/*       */     //   3243: iand
/*       */     //   3244: ior
/*       */     //   3245: putfield blocking : Z
/*       */     //   3248: aload_0
/*       */     //   3249: aload_0
/*       */     //   3250: getfield blocking : Z
/*       */     //   3253: ifne -> 3259
/*       */     //   3256: goto -> 3314
/*       */     //   3259: aload_0
/*       */     //   3260: dup
/*       */     //   3261: getfield blockTicks : I
/*       */     //   3264: sipush #22209
/*       */     //   3267: istore #23
/*       */     //   3269: iload #23
/*       */     //   3271: ldc2_w 9045909571641562816
/*       */     //   3274: l2i
/*       */     //   3275: istore #25
/*       */     //   3277: istore #24
/*       */     //   3279: iload #24
/*       */     //   3281: iload #25
/*       */     //   3283: ior
/*       */     //   3284: iload #24
/*       */     //   3286: iconst_m1
/*       */     //   3287: ixor
/*       */     //   3288: iload #25
/*       */     //   3290: iconst_m1
/*       */     //   3291: ixor
/*       */     //   3292: ior
/*       */     //   3293: iand
/*       */     //   3294: istore #25
/*       */     //   3296: istore #24
/*       */     //   3298: iload #24
/*       */     //   3300: iload #25
/*       */     //   3302: iconst_m1
/*       */     //   3303: ixor
/*       */     //   3304: isub
/*       */     //   3305: iconst_1
/*       */     //   3306: isub
/*       */     //   3307: dup_x1
/*       */     //   3308: putfield blockTicks : I
/*       */     //   3311: goto -> 3340
/*       */     //   3314: sipush #2325
/*       */     //   3317: ldc2_w 4987135738567985429
/*       */     //   3320: l2i
/*       */     //   3321: istore #25
/*       */     //   3323: istore #24
/*       */     //   3325: iload #24
/*       */     //   3327: iload #25
/*       */     //   3329: ior
/*       */     //   3330: iload #24
/*       */     //   3332: iconst_m1
/*       */     //   3333: ixor
/*       */     //   3334: iload #25
/*       */     //   3336: iconst_m1
/*       */     //   3337: ixor
/*       */     //   3338: ior
/*       */     //   3339: iand
/*       */     //   3340: putfield blockTicks : I
/*       */     //   3343: aload_0
/*       */     //   3344: getfield effects : Lme/levansj01/lh;
/*       */     //   3347: <illegal opcode> bbhx : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   3352: checkcast [Lorg/bukkit/potion/PotionEffect;
/*       */     //   3355: checkcast [Lorg/bukkit/potion/PotionEffect;
/*       */     //   3358: astore #11
/*       */     //   3360: aload #11
/*       */     //   3362: checkcast [Lorg/bukkit/potion/PotionEffect;
/*       */     //   3365: getstatic org/bukkit/potion/PotionEffectType.JUMP : Lorg/bukkit/potion/PotionEffectType;
/*       */     //   3368: <illegal opcode> bbhy : (Ljava/lang/Object;)I
/*       */     //   3373: <illegal opcode> bbhz : ([Lorg/bukkit/potion/PotionEffect;I)Z
/*       */     //   3378: ifne -> 3384
/*       */     //   3381: goto -> 3396
/*       */     //   3384: aload_0
/*       */     //   3385: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   3388: getstatic me/levansj01/ij.JUMP_BOOST : Lme/levansj01/ij;
/*       */     //   3391: <illegal opcode> bbia : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   3396: aload #11
/*       */     //   3398: checkcast [Lorg/bukkit/potion/PotionEffect;
/*       */     //   3401: getstatic org/bukkit/potion/PotionEffectType.SPEED : Lorg/bukkit/potion/PotionEffectType;
/*       */     //   3404: <illegal opcode> bbib : (Ljava/lang/Object;)I
/*       */     //   3409: <illegal opcode> bbic : ([Lorg/bukkit/potion/PotionEffect;I)Z
/*       */     //   3414: ifne -> 3420
/*       */     //   3417: goto -> 3432
/*       */     //   3420: aload_0
/*       */     //   3421: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   3424: getstatic me/levansj01/ij.SPEED_BOOST : Lme/levansj01/ij;
/*       */     //   3427: <illegal opcode> bbid : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   3432: aload #11
/*       */     //   3434: checkcast [Lorg/bukkit/potion/PotionEffect;
/*       */     //   3437: sipush #4940
/*       */     //   3440: sipush #4949
/*       */     //   3443: istore #25
/*       */     //   3445: istore #24
/*       */     //   3447: iload #24
/*       */     //   3449: iload #25
/*       */     //   3451: iconst_m1
/*       */     //   3452: ixor
/*       */     //   3453: iand
/*       */     //   3454: iload #24
/*       */     //   3456: iconst_m1
/*       */     //   3457: ixor
/*       */     //   3458: iload #25
/*       */     //   3460: iand
/*       */     //   3461: ior
/*       */     //   3462: <illegal opcode> bbie : ([Lorg/bukkit/potion/PotionEffect;I)Z
/*       */     //   3467: ifne -> 3473
/*       */     //   3470: goto -> 3485
/*       */     //   3473: aload_0
/*       */     //   3474: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   3477: getstatic me/levansj01/ij.LEVITATION : Lme/levansj01/ij;
/*       */     //   3480: <illegal opcode> bbif : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   3485: aload #6
/*       */     //   3487: aload_0
/*       */     //   3488: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   3491: <illegal opcode> bbig : (Ljava/lang/Object;Lorg/bukkit/entity/Player;)Z
/*       */     //   3496: ifne -> 3502
/*       */     //   3499: goto -> 3514
/*       */     //   3502: aload_0
/*       */     //   3503: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   3506: getstatic me/levansj01/ij.GLIDING : Lme/levansj01/ij;
/*       */     //   3509: <illegal opcode> bbih : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   3514: aload_0
/*       */     //   3515: dup
/*       */     //   3516: getfield totalTicks : I
/*       */     //   3519: sipush #15036
/*       */     //   3522: istore #23
/*       */     //   3524: iload #23
/*       */     //   3526: ldc2_w -1682914752331760963
/*       */     //   3529: l2i
/*       */     //   3530: istore #25
/*       */     //   3532: istore #24
/*       */     //   3534: iload #24
/*       */     //   3536: iload #25
/*       */     //   3538: iconst_m1
/*       */     //   3539: ixor
/*       */     //   3540: iand
/*       */     //   3541: iload #24
/*       */     //   3543: iconst_m1
/*       */     //   3544: ixor
/*       */     //   3545: iload #25
/*       */     //   3547: iand
/*       */     //   3548: ior
/*       */     //   3549: istore #25
/*       */     //   3551: istore #24
/*       */     //   3553: iload #24
/*       */     //   3555: iload #25
/*       */     //   3557: ior
/*       */     //   3558: iload #24
/*       */     //   3560: iload #25
/*       */     //   3562: iand
/*       */     //   3563: iadd
/*       */     //   3564: putfield totalTicks : I
/*       */     //   3567: aload_0
/*       */     //   3568: dup
/*       */     //   3569: getfield lastAttackTicks : I
/*       */     //   3572: sipush #31008
/*       */     //   3575: istore #23
/*       */     //   3577: iload #23
/*       */     //   3579: sipush #31009
/*       */     //   3582: istore #25
/*       */     //   3584: istore #24
/*       */     //   3586: iload #24
/*       */     //   3588: iload #25
/*       */     //   3590: iconst_m1
/*       */     //   3591: ixor
/*       */     //   3592: iand
/*       */     //   3593: iload #24
/*       */     //   3595: iconst_m1
/*       */     //   3596: ixor
/*       */     //   3597: iload #25
/*       */     //   3599: iand
/*       */     //   3600: ior
/*       */     //   3601: istore #25
/*       */     //   3603: istore #24
/*       */     //   3605: iload #24
/*       */     //   3607: iload #25
/*       */     //   3609: ior
/*       */     //   3610: iload #24
/*       */     //   3612: iload #25
/*       */     //   3614: iand
/*       */     //   3615: iadd
/*       */     //   3616: putfield lastAttackTicks : I
/*       */     //   3619: aload_0
/*       */     //   3620: dup
/*       */     //   3621: getfield velocityTicks : I
/*       */     //   3624: sipush #28665
/*       */     //   3627: istore #23
/*       */     //   3629: iload #23
/*       */     //   3631: sipush #28664
/*       */     //   3634: istore #25
/*       */     //   3636: istore #24
/*       */     //   3638: iload #24
/*       */     //   3640: iload #25
/*       */     //   3642: ior
/*       */     //   3643: iload #24
/*       */     //   3645: iload #25
/*       */     //   3647: iand
/*       */     //   3648: iconst_m1
/*       */     //   3649: ixor
/*       */     //   3650: iand
/*       */     //   3651: istore #25
/*       */     //   3653: istore #24
/*       */     //   3655: iload #24
/*       */     //   3657: iload #25
/*       */     //   3659: iconst_m1
/*       */     //   3660: ixor
/*       */     //   3661: isub
/*       */     //   3662: iconst_1
/*       */     //   3663: isub
/*       */     //   3664: putfield velocityTicks : I
/*       */     //   3667: aload_0
/*       */     //   3668: dup
/*       */     //   3669: getfield horizontalSpeedTicks : I
/*       */     //   3672: sipush #27082
/*       */     //   3675: sipush #27083
/*       */     //   3678: istore #25
/*       */     //   3680: istore #24
/*       */     //   3682: iload #24
/*       */     //   3684: iload #25
/*       */     //   3686: iconst_m1
/*       */     //   3687: ixor
/*       */     //   3688: iand
/*       */     //   3689: iload #24
/*       */     //   3691: iconst_m1
/*       */     //   3692: ixor
/*       */     //   3693: iload #25
/*       */     //   3695: iand
/*       */     //   3696: ior
/*       */     //   3697: istore #25
/*       */     //   3699: istore #24
/*       */     //   3701: iconst_2
/*       */     //   3702: iload #24
/*       */     //   3704: iload #25
/*       */     //   3706: ior
/*       */     //   3707: imul
/*       */     //   3708: iload #24
/*       */     //   3710: iload #25
/*       */     //   3712: ixor
/*       */     //   3713: isub
/*       */     //   3714: putfield horizontalSpeedTicks : I
/*       */     //   3717: aload_0
/*       */     //   3718: dup
/*       */     //   3719: getfield verticalVelocityTicks : I
/*       */     //   3722: sipush #7787
/*       */     //   3725: ldc2_w -6986674620967412118
/*       */     //   3728: l2i
/*       */     //   3729: istore #25
/*       */     //   3731: istore #24
/*       */     //   3733: iload #24
/*       */     //   3735: iload #25
/*       */     //   3737: ior
/*       */     //   3738: iload #24
/*       */     //   3740: iload #25
/*       */     //   3742: iand
/*       */     //   3743: iconst_m1
/*       */     //   3744: ixor
/*       */     //   3745: iand
/*       */     //   3746: istore #25
/*       */     //   3748: istore #24
/*       */     //   3750: iload #24
/*       */     //   3752: iload #25
/*       */     //   3754: iconst_m1
/*       */     //   3755: ixor
/*       */     //   3756: isub
/*       */     //   3757: iconst_1
/*       */     //   3758: isub
/*       */     //   3759: putfield verticalVelocityTicks : I
/*       */     //   3762: aload_0
/*       */     //   3763: dup
/*       */     //   3764: getfield horizontalVelocityTicks : I
/*       */     //   3767: sipush #27846
/*       */     //   3770: istore #23
/*       */     //   3772: ldc2_w 6019864554786286791
/*       */     //   3775: l2i
/*       */     //   3776: iload #23
/*       */     //   3778: istore #25
/*       */     //   3780: istore #24
/*       */     //   3782: iload #24
/*       */     //   3784: iload #25
/*       */     //   3786: iconst_m1
/*       */     //   3787: ixor
/*       */     //   3788: iand
/*       */     //   3789: iload #24
/*       */     //   3791: iconst_m1
/*       */     //   3792: ixor
/*       */     //   3793: iload #25
/*       */     //   3795: iand
/*       */     //   3796: ior
/*       */     //   3797: istore #25
/*       */     //   3799: istore #24
/*       */     //   3801: iload #24
/*       */     //   3803: iload #25
/*       */     //   3805: iconst_m1
/*       */     //   3806: ixor
/*       */     //   3807: isub
/*       */     //   3808: iconst_1
/*       */     //   3809: isub
/*       */     //   3810: putfield horizontalVelocityTicks : I
/*       */     //   3813: aload_0
/*       */     //   3814: aload_0
/*       */     //   3815: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   3818: aload_0
/*       */     //   3819: getfield location : Lme/levansj01/gz;
/*       */     //   3822: <illegal opcode> bbii : (Ljava/lang/Object;Lme/levansj01/ir;)Z
/*       */     //   3827: ifeq -> 3833
/*       */     //   3830: goto -> 3862
/*       */     //   3833: sipush #30699
/*       */     //   3836: istore #23
/*       */     //   3838: iload #23
/*       */     //   3840: ldc2_w 4381702395311192042
/*       */     //   3843: l2i
/*       */     //   3844: istore #25
/*       */     //   3846: istore #24
/*       */     //   3848: iload #24
/*       */     //   3850: iload #25
/*       */     //   3852: ior
/*       */     //   3853: iload #24
/*       */     //   3855: iload #25
/*       */     //   3857: iand
/*       */     //   3858: isub
/*       */     //   3859: goto -> 3886
/*       */     //   3862: ldc2_w -3570116767944528090
/*       */     //   3865: l2i
/*       */     //   3866: sipush #12070
/*       */     //   3869: istore #25
/*       */     //   3871: istore #24
/*       */     //   3873: iload #24
/*       */     //   3875: iload #25
/*       */     //   3877: ior
/*       */     //   3878: iload #24
/*       */     //   3880: iload #25
/*       */     //   3882: iand
/*       */     //   3883: iconst_m1
/*       */     //   3884: ixor
/*       */     //   3885: iand
/*       */     //   3886: putfield moved : Z
/*       */     //   3889: aload_0
/*       */     //   3890: getfield moved : Z
/*       */     //   3893: ifne -> 3899
/*       */     //   3896: goto -> 3989
/*       */     //   3899: aload_0
/*       */     //   3900: getfield checkData : Lme/levansj01/fc;
/*       */     //   3903: <illegal opcode> bbij : (Ljava/lang/Object;)[Lme/levansj01/hm;
/*       */     //   3908: checkcast [Lme/levansj01/hm;
/*       */     //   3911: astore #12
/*       */     //   3913: aload #12
/*       */     //   3915: arraylength
/*       */     //   3916: istore #13
/*       */     //   3918: sipush #23949
/*       */     //   3921: istore #23
/*       */     //   3923: iload #23
/*       */     //   3925: sipush #23949
/*       */     //   3928: istore #25
/*       */     //   3930: istore #24
/*       */     //   3932: iload #24
/*       */     //   3934: iload #25
/*       */     //   3936: ior
/*       */     //   3937: iload #24
/*       */     //   3939: iconst_m1
/*       */     //   3940: ixor
/*       */     //   3941: iload #25
/*       */     //   3943: iconst_m1
/*       */     //   3944: ixor
/*       */     //   3945: ior
/*       */     //   3946: iand
/*       */     //   3947: istore #14
/*       */     //   3949: iload #14
/*       */     //   3951: iload #13
/*       */     //   3953: if_icmplt -> 3959
/*       */     //   3956: goto -> 3989
/*       */     //   3959: aload #12
/*       */     //   3961: iload #14
/*       */     //   3963: aaload
/*       */     //   3964: astore #15
/*       */     //   3966: aload #15
/*       */     //   3968: aload_0
/*       */     //   3969: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   3972: aload_0
/*       */     //   3973: getfield location : Lme/levansj01/gz;
/*       */     //   3976: lload #4
/*       */     //   3978: <illegal opcode> bbik : (Ljava/lang/Object;Lme/levansj01/gz;Lme/levansj01/gz;J)V
/*       */     //   3983: iinc #14, 1
/*       */     //   3986: goto -> 3949
/*       */     //   3989: aload_0
/*       */     //   3990: aload_0
/*       */     //   3991: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   3994: aload_0
/*       */     //   3995: getfield location : Lme/levansj01/gz;
/*       */     //   3998: <illegal opcode> bbil : (Ljava/lang/Object;Lme/levansj01/ir;)Z
/*       */     //   4003: ifeq -> 4009
/*       */     //   4006: goto -> 4038
/*       */     //   4009: sipush #21616
/*       */     //   4012: ldc2_w -1091775571337128847
/*       */     //   4015: l2i
/*       */     //   4016: istore #25
/*       */     //   4018: istore #24
/*       */     //   4020: iload #24
/*       */     //   4022: iload #25
/*       */     //   4024: iconst_m1
/*       */     //   4025: ixor
/*       */     //   4026: iand
/*       */     //   4027: iload #24
/*       */     //   4029: iconst_m1
/*       */     //   4030: ixor
/*       */     //   4031: iload #25
/*       */     //   4033: iand
/*       */     //   4034: ior
/*       */     //   4035: goto -> 4060
/*       */     //   4038: sipush #9332
/*       */     //   4041: ldc2_w 2500065334464095348
/*       */     //   4044: l2i
/*       */     //   4045: istore #25
/*       */     //   4047: istore #24
/*       */     //   4049: iload #24
/*       */     //   4051: iload #25
/*       */     //   4053: ior
/*       */     //   4054: iload #24
/*       */     //   4056: iload #25
/*       */     //   4058: iand
/*       */     //   4059: isub
/*       */     //   4060: dup_x1
/*       */     //   4061: putfield aimed : Z
/*       */     //   4064: ifne -> 4070
/*       */     //   4067: goto -> 4153
/*       */     //   4070: aload_0
/*       */     //   4071: getfield checkData : Lme/levansj01/fc;
/*       */     //   4074: <illegal opcode> bbim : (Ljava/lang/Object;)[Lme/levansj01/gv;
/*       */     //   4079: checkcast [Lme/levansj01/gv;
/*       */     //   4082: astore #12
/*       */     //   4084: aload #12
/*       */     //   4086: arraylength
/*       */     //   4087: istore #13
/*       */     //   4089: ldc2_w -1133726393817876690
/*       */     //   4092: l2i
/*       */     //   4093: sipush #14126
/*       */     //   4096: istore #25
/*       */     //   4098: istore #24
/*       */     //   4100: iload #24
/*       */     //   4102: iload #25
/*       */     //   4104: ior
/*       */     //   4105: iload #24
/*       */     //   4107: iload #25
/*       */     //   4109: iand
/*       */     //   4110: isub
/*       */     //   4111: istore #14
/*       */     //   4113: iload #14
/*       */     //   4115: iload #13
/*       */     //   4117: if_icmplt -> 4123
/*       */     //   4120: goto -> 4153
/*       */     //   4123: aload #12
/*       */     //   4125: iload #14
/*       */     //   4127: aaload
/*       */     //   4128: astore #15
/*       */     //   4130: aload #15
/*       */     //   4132: aload_0
/*       */     //   4133: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   4136: aload_0
/*       */     //   4137: getfield location : Lme/levansj01/gz;
/*       */     //   4140: lload #4
/*       */     //   4142: <illegal opcode> bbin : (Ljava/lang/Object;Lme/levansj01/gz;Lme/levansj01/gz;J)V
/*       */     //   4147: iinc #14, 1
/*       */     //   4150: goto -> 4113
/*       */     //   4153: aload_0
/*       */     //   4154: aload_0
/*       */     //   4155: getfield lastLocation : Lme/levansj01/gz;
/*       */     //   4158: <illegal opcode> bbio : (Ljava/lang/Object;)Lme/levansj01/gz;
/*       */     //   4163: putfield lastLastLocation : Lme/levansj01/gz;
/*       */     //   4166: aload_0
/*       */     //   4167: aload_0
/*       */     //   4168: getfield location : Lme/levansj01/gz;
/*       */     //   4171: <illegal opcode> bbip : (Ljava/lang/Object;)Lme/levansj01/gz;
/*       */     //   4176: putfield lastLocation : Lme/levansj01/gz;
/*       */     //   4179: aload_0
/*       */     //   4180: getfield lastAttackTicks : I
/*       */     //   4183: sipush #3362
/*       */     //   4186: ldc2_w -3860641464086164189
/*       */     //   4189: l2i
/*       */     //   4190: istore #25
/*       */     //   4192: istore #24
/*       */     //   4194: iload #24
/*       */     //   4196: iload #25
/*       */     //   4198: ior
/*       */     //   4199: iload #24
/*       */     //   4201: iload #25
/*       */     //   4203: iand
/*       */     //   4204: iconst_m1
/*       */     //   4205: ixor
/*       */     //   4206: iand
/*       */     //   4207: if_icmple -> 4213
/*       */     //   4210: goto -> 4770
/*       */     //   4213: aload_0
/*       */     //   4214: getfield lastAttacked : Lme/levansj01/dc;
/*       */     //   4217: ifnull -> 4770
/*       */     //   4220: aload_0
/*       */     //   4221: sipush #13364
/*       */     //   4224: istore #23
/*       */     //   4226: ldc2_w 3062976916647588913
/*       */     //   4229: l2i
/*       */     //   4230: iload #23
/*       */     //   4232: istore #25
/*       */     //   4234: istore #24
/*       */     //   4236: iload #24
/*       */     //   4238: iload #25
/*       */     //   4240: ior
/*       */     //   4241: iload #24
/*       */     //   4243: iconst_m1
/*       */     //   4244: ixor
/*       */     //   4245: iload #25
/*       */     //   4247: iconst_m1
/*       */     //   4248: ixor
/*       */     //   4249: ior
/*       */     //   4250: iand
/*       */     //   4251: <illegal opcode> bbiq : (Ljava/lang/Object;I)Z
/*       */     //   4256: ifeq -> 4262
/*       */     //   4259: goto -> 4770
/*       */     //   4262: aload_0
/*       */     //   4263: <illegal opcode> bbir : (Ljava/lang/Object;)Z
/*       */     //   4268: ifeq -> 4274
/*       */     //   4271: goto -> 4770
/*       */     //   4274: aload_0
/*       */     //   4275: <illegal opcode> bbis : (Ljava/lang/Object;)Z
/*       */     //   4280: ifne -> 4286
/*       */     //   4283: goto -> 4770
/*       */     //   4286: aload_0
/*       */     //   4287: <illegal opcode> bbit : (Ljava/lang/Object;)Z
/*       */     //   4292: ifeq -> 4298
/*       */     //   4295: goto -> 4770
/*       */     //   4298: aload_0
/*       */     //   4299: getfield lastAttacked : Lme/levansj01/dc;
/*       */     //   4302: <illegal opcode> bbiu : (Ljava/lang/Object;)I
/*       */     //   4307: aload_0
/*       */     //   4308: getfield lastAttacked : Lme/levansj01/dc;
/*       */     //   4311: <illegal opcode> bbiv : (Ljava/lang/Object;)I
/*       */     //   4316: aload_0
/*       */     //   4317: <illegal opcode> bbiw : (Ljava/lang/Object;)I
/*       */     //   4322: istore #25
/*       */     //   4324: istore #24
/*       */     //   4326: iconst_2
/*       */     //   4327: iload #24
/*       */     //   4329: iload #25
/*       */     //   4331: ior
/*       */     //   4332: imul
/*       */     //   4333: iload #24
/*       */     //   4335: iload #25
/*       */     //   4337: ixor
/*       */     //   4338: isub
/*       */     //   4339: if_icmpgt -> 4345
/*       */     //   4342: goto -> 4770
/*       */     //   4345: aload_0
/*       */     //   4346: getfield lastAttacked : Lme/levansj01/dc;
/*       */     //   4349: <illegal opcode> bbix : (Ljava/lang/Object;)Lme/levansj01/jz;
/*       */     //   4354: getstatic me/levansj01/ij.VEHICLE : Lme/levansj01/ij;
/*       */     //   4357: <illegal opcode> bbiy : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   4362: aload_0
/*       */     //   4363: getfield lastAttacked : Lme/levansj01/dc;
/*       */     //   4366: <illegal opcode> bbiz : (Ljava/lang/Object;)I
/*       */     //   4371: aload_0
/*       */     //   4372: <illegal opcode> bbja : (Ljava/lang/Object;)I
/*       */     //   4377: istore #25
/*       */     //   4379: istore #24
/*       */     //   4381: iconst_2
/*       */     //   4382: iload #24
/*       */     //   4384: iload #25
/*       */     //   4386: ior
/*       */     //   4387: imul
/*       */     //   4388: iload #24
/*       */     //   4390: iload #25
/*       */     //   4392: ixor
/*       */     //   4393: isub
/*       */     //   4394: if_icmpgt -> 4400
/*       */     //   4397: goto -> 4770
/*       */     //   4400: <illegal opcode> bbjb : ()Lme/levansj01/on;
/*       */     //   4405: <illegal opcode> bbjc : (Ljava/lang/Object;)Lme/levansj01/kh;
/*       */     //   4410: <illegal opcode> bbjd : (Ljava/lang/Object;)Z
/*       */     //   4415: ifne -> 4421
/*       */     //   4418: goto -> 4433
/*       */     //   4421: aload_0
/*       */     //   4422: <illegal opcode> bbje : (Ljava/lang/Object;)Z
/*       */     //   4427: ifeq -> 4433
/*       */     //   4430: goto -> 4445
/*       */     //   4433: aload_0
/*       */     //   4434: <illegal opcode> bbjf : (Ljava/lang/Object;)Z
/*       */     //   4439: ifne -> 4445
/*       */     //   4442: goto -> 4476
/*       */     //   4445: sipush #15883
/*       */     //   4448: istore #23
/*       */     //   4450: iload #23
/*       */     //   4452: ldc2_w -1749911985836769782
/*       */     //   4455: l2i
/*       */     //   4456: istore #25
/*       */     //   4458: istore #24
/*       */     //   4460: iload #24
/*       */     //   4462: iload #25
/*       */     //   4464: ior
/*       */     //   4465: iload #24
/*       */     //   4467: iload #25
/*       */     //   4469: iand
/*       */     //   4470: iconst_m1
/*       */     //   4471: ixor
/*       */     //   4472: iand
/*       */     //   4473: goto -> 4506
/*       */     //   4476: sipush #15568
/*       */     //   4479: istore #23
/*       */     //   4481: ldc2_w 4467596389701926096
/*       */     //   4484: l2i
/*       */     //   4485: iload #23
/*       */     //   4487: istore #25
/*       */     //   4489: istore #24
/*       */     //   4491: iload #24
/*       */     //   4493: iload #25
/*       */     //   4495: iconst_m1
/*       */     //   4496: ixor
/*       */     //   4497: iand
/*       */     //   4498: iload #24
/*       */     //   4500: iconst_m1
/*       */     //   4501: ixor
/*       */     //   4502: iload #25
/*       */     //   4504: iand
/*       */     //   4505: ior
/*       */     //   4506: istore #12
/*       */     //   4508: aload_0
/*       */     //   4509: getfield sneaking : Ljava/lang/Boolean;
/*       */     //   4512: ifnull -> 4555
/*       */     //   4515: aload_0
/*       */     //   4516: getfield sneaking : Ljava/lang/Boolean;
/*       */     //   4519: <illegal opcode> bbjg : (Ljava/lang/Object;)Z
/*       */     //   4524: ifne -> 4530
/*       */     //   4527: goto -> 4555
/*       */     //   4530: ldc2_w -6776709849007760627
/*       */     //   4533: l2i
/*       */     //   4534: sipush #23308
/*       */     //   4537: istore #25
/*       */     //   4539: istore #24
/*       */     //   4541: iload #24
/*       */     //   4543: iload #25
/*       */     //   4545: ior
/*       */     //   4546: iload #24
/*       */     //   4548: iload #25
/*       */     //   4550: iand
/*       */     //   4551: isub
/*       */     //   4552: goto -> 4584
/*       */     //   4555: sipush #32526
/*       */     //   4558: istore #23
/*       */     //   4560: iload #23
/*       */     //   4562: sipush #32526
/*       */     //   4565: istore #25
/*       */     //   4567: istore #24
/*       */     //   4569: iload #24
/*       */     //   4571: iload #25
/*       */     //   4573: ior
/*       */     //   4574: iload #24
/*       */     //   4576: iconst_m1
/*       */     //   4577: ixor
/*       */     //   4578: iload #25
/*       */     //   4580: iconst_m1
/*       */     //   4581: ixor
/*       */     //   4582: ior
/*       */     //   4583: iand
/*       */     //   4584: istore #13
/*       */     //   4586: aload_0
/*       */     //   4587: getfield location : Lme/levansj01/gz;
/*       */     //   4590: <illegal opcode> bbjh : (Ljava/lang/Object;)Lme/levansj01/gz;
/*       */     //   4595: astore #14
/*       */     //   4597: aload_0
/*       */     //   4598: getfield lastLastLocation : Lme/levansj01/gz;
/*       */     //   4601: <illegal opcode> bbji : (Ljava/lang/Object;)Lme/levansj01/gz;
/*       */     //   4606: astore #15
/*       */     //   4608: aload_0
/*       */     //   4609: getfield recentMoveMap : Ljava/util/Map;
/*       */     //   4612: aload_0
/*       */     //   4613: getfield lastAttackedId : I
/*       */     //   4616: <illegal opcode> bbjj : (I)Ljava/lang/Integer;
/*       */     //   4621: <illegal opcode> bbjk : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   4626: checkcast me/levansj01/ka
/*       */     //   4629: astore #16
/*       */     //   4631: aload #16
/*       */     //   4633: ifnull -> 4770
/*       */     //   4636: aload #16
/*       */     //   4638: <illegal opcode> bbjl : (Ljava/lang/Object;)I
/*       */     //   4643: sipush #30348
/*       */     //   4646: sipush #30372
/*       */     //   4649: istore #25
/*       */     //   4651: istore #24
/*       */     //   4653: iload #24
/*       */     //   4655: iload #25
/*       */     //   4657: ior
/*       */     //   4658: iload #24
/*       */     //   4660: iconst_m1
/*       */     //   4661: ixor
/*       */     //   4662: iload #25
/*       */     //   4664: iconst_m1
/*       */     //   4665: ixor
/*       */     //   4666: ior
/*       */     //   4667: iand
/*       */     //   4668: ldc2_w -6994244539481301388
/*       */     //   4671: l2i
/*       */     //   4672: sipush #24190
/*       */     //   4675: istore #25
/*       */     //   4677: istore #24
/*       */     //   4679: iload #24
/*       */     //   4681: iload #25
/*       */     //   4683: ior
/*       */     //   4684: iload #24
/*       */     //   4686: iload #25
/*       */     //   4688: iand
/*       */     //   4689: iconst_m1
/*       */     //   4690: ixor
/*       */     //   4691: iand
/*       */     //   4692: aload_0
/*       */     //   4693: <illegal opcode> bbjm : (Ljava/lang/Object;)I
/*       */     //   4698: istore #25
/*       */     //   4700: istore #24
/*       */     //   4702: iconst_2
/*       */     //   4703: iload #24
/*       */     //   4705: iload #25
/*       */     //   4707: ior
/*       */     //   4708: imul
/*       */     //   4709: iload #24
/*       */     //   4711: iload #25
/*       */     //   4713: ixor
/*       */     //   4714: isub
/*       */     //   4715: <illegal opcode> bbjn : (II)I
/*       */     //   4720: if_icmpge -> 4726
/*       */     //   4723: goto -> 4770
/*       */     //   4726: aload_0
/*       */     //   4727: getfield transactionPing : I
/*       */     //   4730: istore #17
/*       */     //   4732: aload_0
/*       */     //   4733: getfield nonMoveTicks : I
/*       */     //   4736: istore #18
/*       */     //   4738: aload_0
/*       */     //   4739: getfield pingQueue : Ljava/util/Queue;
/*       */     //   4742: aload_0
/*       */     //   4743: lload #4
/*       */     //   4745: aload #16
/*       */     //   4747: aload #14
/*       */     //   4749: aload #15
/*       */     //   4751: iload #17
/*       */     //   4753: iload #18
/*       */     //   4755: iload #13
/*       */     //   4757: iload #12
/*       */     //   4759: <illegal opcode> accept : (Lme/levansj01/dc;JLme/levansj01/ka;Lme/levansj01/gz;Lme/levansj01/gz;IIZZ)Ljava/util/function/BiConsumer;
/*       */     //   4764: <illegal opcode> bbjo : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   4769: pop
/*       */     //   4770: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #9510	-> 0
/*       */     //   #26796	-> 10
/*       */     //   #22729	-> 20
/*       */     //   #7247	-> 74
/*       */     //   #57727	-> 104
/*       */     //   #46929	-> 110
/*       */     //   #22429	-> 119
/*       */     //   #57634	-> 128
/*       */     //   #61325	-> 152
/*       */     //   #55303	-> 164
/*       */     //   #20406	-> 192
/*       */     //   #10986	-> 205
/*       */     //   #24909	-> 212
/*       */     //   #24581	-> 228
/*       */     //   #52532	-> 240
/*       */     //   #48580	-> 293
/*       */     //   #14707	-> 329
/*       */     //   #43160	-> 343
/*       */     //   #10870	-> 355
/*       */     //   #32950	-> 367
/*       */     //   #690	-> 379
/*       */     //   #24918	-> 385
/*       */     //   #22009	-> 400
/*       */     //   #30522	-> 418
/*       */     //   #15375	-> 421
/*       */     //   #60345	-> 643
/*       */     //   #3387	-> 655
/*       */     //   #54109	-> 658
/*       */     //   #18215	-> 660
/*       */     //   #8376	-> 667
/*       */     //   #21981	-> 670
/*       */     //   #24618	-> 682
/*       */     //   #19274	-> 696
/*       */     //   #53893	-> 703
/*       */     //   #48107	-> 739
/*       */     //   #15579	-> 754
/*       */     //   #13055	-> 763
/*       */     //   #25410	-> 772
/*       */     //   #15892	-> 781
/*       */     //   #15315	-> 790
/*       */     //   #64841	-> 796
/*       */     //   #53461	-> 815
/*       */     //   #18449	-> 823
/*       */     //   #47	-> 831
/*       */     //   #39934	-> 845
/*       */     //   #2754	-> 878
/*       */     //   #3540	-> 890
/*       */     //   #39101	-> 952
/*       */     //   #65341	-> 969
/*       */     //   #31025	-> 975
/*       */     //   #21939	-> 986
/*       */     //   #48038	-> 1001
/*       */     //   #47385	-> 1021
/*       */     //   #16619	-> 1033
/*       */     //   #4110	-> 1048
/*       */     //   #55089	-> 1063
/*       */     //   #62489	-> 1078
/*       */     //   #46973	-> 1090
/*       */     //   #13854	-> 1105
/*       */     //   #58733	-> 1120
/*       */     //   #39425	-> 1173
/*       */     //   #47309	-> 1185
/*       */     //   #16339	-> 1218
/*       */     //   #57127	-> 1227
/*       */     //   #47176	-> 1239
/*       */     //   #53020	-> 1245
/*       */     //   #13048	-> 1257
/*       */     //   #50313	-> 1263
/*       */     //   #38726	-> 1271
/*       */     //   #58292	-> 1277
/*       */     //   #14261	-> 1407
/*       */     //   #45437	-> 1420
/*       */     //   #26854	-> 1432
/*       */     //   #30879	-> 1489
/*       */     //   #12237	-> 1503
/*       */     //   #14507	-> 1521
/*       */     //   #19130	-> 1533
/*       */     //   #48246	-> 1564
/*       */     //   #28331	-> 1576
/*       */     //   #47197	-> 1588
/*       */     //   #3897	-> 1598
/*       */     //   #24294	-> 1628
/*       */     //   #15056	-> 1638
/*       */     //   #26754	-> 1668
/*       */     //   #27055	-> 1698
/*       */     //   #14987	-> 1726
/*       */     //   #11239	-> 1736
/*       */     //   #28908	-> 1766
/*       */     //   #8672	-> 1794
/*       */     //   #10695	-> 1804
/*       */     //   #53103	-> 1809
/*       */     //   #49320	-> 1839
/*       */     //   #49895	-> 1856
/*       */     //   #12098	-> 1881
/*       */     //   #59577	-> 1923
/*       */     //   #26968	-> 1933
/*       */     //   #27992	-> 1956
/*       */     //   #3059	-> 1986
/*       */     //   #62826	-> 1999
/*       */     //   #63540	-> 2007
/*       */     //   #52696	-> 2020
/*       */     //   #26277	-> 2033
/*       */     //   #60153	-> 2044
/*       */     //   #44852	-> 2131
/*       */     //   #56648	-> 2141
/*       */     //   #46543	-> 2165
/*       */     //   #16945	-> 2177
/*       */     //   #59892	-> 2185
/*       */     //   #42208	-> 2197
/*       */     //   #52920	-> 2209
/*       */     //   #10946	-> 2221
/*       */     //   #12802	-> 2231
/*       */     //   #49451	-> 2264
/*       */     //   #35602	-> 2306
/*       */     //   #42857	-> 2318
/*       */     //   #50479	-> 2350
/*       */     //   #31700	-> 2390
/*       */     //   #28545	-> 2436
/*       */     //   #38520	-> 2448
/*       */     //   #59786	-> 2473
/*       */     //   #42920	-> 2523
/*       */     //   #46746	-> 2567
/*       */     //   #2424	-> 2620
/*       */     //   #29322	-> 2714
/*       */     //   #58038	-> 2812
/*       */     //   #29312	-> 2822
/*       */     //   #13872	-> 2837
/*       */     //   #65290	-> 2850
/*       */     //   #5825	-> 2872
/*       */     //   #55545	-> 2901
/*       */     //   #4343	-> 2916
/*       */     //   #24876	-> 2935
/*       */     //   #33509	-> 2965
/*       */     //   #32315	-> 2976
/*       */     //   #25085	-> 3074
/*       */     //   #63672	-> 3113
/*       */     //   #27210	-> 3145
/*       */     //   #23244	-> 3214
/*       */     //   #56703	-> 3248
/*       */     //   #8313	-> 3343
/*       */     //   #36320	-> 3360
/*       */     //   #44900	-> 3384
/*       */     //   #13487	-> 3396
/*       */     //   #32572	-> 3420
/*       */     //   #36561	-> 3432
/*       */     //   #16725	-> 3473
/*       */     //   #25776	-> 3485
/*       */     //   #57071	-> 3502
/*       */     //   #41120	-> 3514
/*       */     //   #33053	-> 3567
/*       */     //   #20736	-> 3619
/*       */     //   #38875	-> 3667
/*       */     //   #62433	-> 3717
/*       */     //   #37814	-> 3762
/*       */     //   #8536	-> 3813
/*       */     //   #22468	-> 3889
/*       */     //   #16017	-> 3899
/*       */     //   #46344	-> 3966
/*       */     //   #49041	-> 3983
/*       */     //   #43911	-> 3989
/*       */     //   #18773	-> 4070
/*       */     //   #50899	-> 4130
/*       */     //   #42257	-> 4147
/*       */     //   #19708	-> 4153
/*       */     //   #9836	-> 4166
/*       */     //   #53251	-> 4179
/*       */     //   #53942	-> 4400
/*       */     //   #64359	-> 4508
/*       */     //   #18770	-> 4586
/*       */     //   #8726	-> 4597
/*       */     //   #40096	-> 4608
/*       */     //   #3903	-> 4631
/*       */     //   #42175	-> 4726
/*       */     //   #40092	-> 4732
/*       */     //   #44696	-> 4738
/*       */     //   #19536	-> 4770
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   2976	1795	10	brtx	Lorg/bukkit/GameMode;
/*       */     //   952	17	10	brte	Lme/levansj01/et;
/*       */     //   1503	3268	9	brtw	I
/*       */     //   3156	1615	2	brtr	Lorg/bukkit/inventory/ItemStack;
/*       */     //   0	4771	1	brtq	Lme/levansj01/l;
/*       */     //   4597	173	14	brtm	Lme/levansj01/gz;
/*       */     //   110	4661	4	brtt	J
/*       */     //   1227	3544	7	brtv	J
/*       */     //   4738	32	18	brtj	I
/*       */     //   3966	17	15	brtg	Lme/levansj01/hm;
/*       */     //   660	7	8	brtc	Ljava/lang/Throwable;
/*       */     //   4732	38	17	brti	I
/*       */     //   4631	139	16	brto	Lme/levansj01/ka;
/*       */     //   4586	184	13	brtl	Z
/*       */     //   4608	162	15	brtn	Lme/levansj01/gz;
/*       */     //   0	4771	0	brtp	Lme/levansj01/dc;
/*       */     //   4508	262	12	brtk	Z
/*       */     //   703	4068	6	brtu	Lme/levansj01/verus/compat/NMSManager;
/*       */     //   329	338	7	brtd	Lme/levansj01/bi;
/*       */     //   4130	17	15	brth	Lme/levansj01/gv;
/*       */     //   1881	260	10	brtf	I
/*       */     //   3360	1411	11	brty	[Lorg/bukkit/potion/PotionEffect;
/*       */     //   1853	2918	3	brts	Lme/levansj01/gz;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	4771	1	brtq	Lme/levansj01/l<*>;
/*       */     // Exception table:
/*       */     //   from	to	target	type
/*       */     //   643	655	658	java/lang/Throwable
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public Queue<bi> getTeleports() {
/*  9613 */     return this.teleports;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getHorizontalVelocityTicks() {
/*  9694 */     return this.horizontalVelocityTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setEnabled(boolean bshj) {
/*  9808 */     this.enabled = bshj;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getTicks() {
/*  9831 */     return this.ticks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isLevitating() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   4: getstatic me/levansj01/ij.LEVITATION : Lme/levansj01/ij;
/*       */     //   7: <illegal opcode> bbuz : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   12: aload_0
/*       */     //   13: <illegal opcode> bbva : (Ljava/lang/Object;)I
/*       */     //   18: sipush #14430
/*       */     //   21: sipush #14428
/*       */     //   24: istore #6
/*       */     //   26: istore #5
/*       */     //   28: iload #5
/*       */     //   30: iload #6
/*       */     //   32: ior
/*       */     //   33: iload #5
/*       */     //   35: iconst_m1
/*       */     //   36: ixor
/*       */     //   37: iload #6
/*       */     //   39: iconst_m1
/*       */     //   40: ixor
/*       */     //   41: ior
/*       */     //   42: iand
/*       */     //   43: istore #6
/*       */     //   45: istore #5
/*       */     //   47: iload #5
/*       */     //   49: iload #6
/*       */     //   51: ior
/*       */     //   52: iload #5
/*       */     //   54: iload #6
/*       */     //   56: iand
/*       */     //   57: iadd
/*       */     //   58: sipush #29526
/*       */     //   61: sipush #29524
/*       */     //   64: istore #6
/*       */     //   66: istore #5
/*       */     //   68: iload #5
/*       */     //   70: iload #6
/*       */     //   72: iconst_m1
/*       */     //   73: ixor
/*       */     //   74: iand
/*       */     //   75: iload #5
/*       */     //   77: iconst_m1
/*       */     //   78: ixor
/*       */     //   79: iload #6
/*       */     //   81: iand
/*       */     //   82: ior
/*       */     //   83: imul
/*       */     //   84: if_icmple -> 90
/*       */     //   87: goto -> 115
/*       */     //   90: ldc2_w -7916354823723651273
/*       */     //   93: l2i
/*       */     //   94: sipush #28470
/*       */     //   97: istore #6
/*       */     //   99: istore #5
/*       */     //   101: iload #5
/*       */     //   103: iload #6
/*       */     //   105: ior
/*       */     //   106: iload #5
/*       */     //   108: iload #6
/*       */     //   110: iand
/*       */     //   111: isub
/*       */     //   112: goto -> 143
/*       */     //   115: sipush #20449
/*       */     //   118: istore #4
/*       */     //   120: ldc2_w -4260058866969980959
/*       */     //   123: l2i
/*       */     //   124: iload #4
/*       */     //   126: istore #6
/*       */     //   128: istore #5
/*       */     //   130: iload #5
/*       */     //   132: iload #6
/*       */     //   134: ior
/*       */     //   135: iload #5
/*       */     //   137: iload #6
/*       */     //   139: iand
/*       */     //   140: iconst_m1
/*       */     //   141: ixor
/*       */     //   142: iand
/*       */     //   143: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #9867	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	144	0	bsat	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void fuckOff() {
/*       */     // Byte code:
/*       */     //   0: <illegal opcode> bbth : ()Lme/levansj01/verus/compat/NMSManager;
/*       */     //   5: astore_1
/*       */     //   6: aload_1
/*       */     //   7: aload_0
/*       */     //   8: <illegal opcode> bbti : (Ljava/lang/Object;Lme/levansj01/dc;)V
/*       */     //   13: aload_0
/*       */     //   14: sipush #16616
/*       */     //   17: ldc2_w -4943324714211720984
/*       */     //   20: l2i
/*       */     //   21: istore_3
/*       */     //   22: istore_2
/*       */     //   23: iload_2
/*       */     //   24: iload_3
/*       */     //   25: ior
/*       */     //   26: iload_2
/*       */     //   27: iconst_m1
/*       */     //   28: ixor
/*       */     //   29: iload_3
/*       */     //   30: iconst_m1
/*       */     //   31: ixor
/*       */     //   32: ior
/*       */     //   33: iand
/*       */     //   34: putfield enabled : Z
/*       */     //   37: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #10226	-> 0
/*       */     //   #958	-> 6
/*       */     //   #61287	-> 13
/*       */     //   #25246	-> 37
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	38	0	brzm	Lme/levansj01/dc;
/*       */     //   6	32	1	brzn	Lme/levansj01/verus/compat/NMSManager;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isDigging() {
/* 10434 */     return this.digging;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public boolean isSurvival() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: sipush #17693
/*       */     //   4: istore #4
/*       */     //   6: ldc2_w 561778093069452572
/*       */     //   9: l2i
/*       */     //   10: iload #4
/*       */     //   12: istore #6
/*       */     //   14: istore #5
/*       */     //   16: iload #5
/*       */     //   18: iload #6
/*       */     //   20: ior
/*       */     //   21: iload #5
/*       */     //   23: iconst_m1
/*       */     //   24: ixor
/*       */     //   25: iload #6
/*       */     //   27: iconst_m1
/*       */     //   28: ixor
/*       */     //   29: ior
/*       */     //   30: iand
/*       */     //   31: <illegal opcode> bbvu : (Ljava/lang/Object;I)Z
/*       */     //   36: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #10811	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	37	0	bsbj	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public ca getVelocityHandler() {
/* 10993 */     return this.velocityHandler;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getLastFakeEntityDamageTicks() {
/* 11055 */     return this.lastFakeEntityDamageTicks;
/*       */   }
/*       */ 
/*       */   
/*       */   public boolean isSpawned() {
/* 11060 */     char c1 = '୕', c2 = 'ऍ'; int i = c1; if (this.spawned <= ((i | c2) & (i & c2 ^ 0xFFFFFFFF))) { c2 = c1 = '剾'; i = (int)2894667894325531262L; return i & (c2 ^ 0xFFFFFFFF) | (i ^ 0xFFFFFFFF) & c2; }  c2 = c1 = 'Э'; i = (int)-5393997346272967636L;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getBlockTicks() {
/* 11112 */     return this.blockTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Queue<em> getSpoofedAlerts() {
/* 11461 */     return this.spoofedAlerts;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(hc<?> bruz) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield world : Lme/levansj01/f;
/*       */     //   4: ifnull -> 17
/*       */     //   7: aload_0
/*       */     //   8: getfield world : Lme/levansj01/f;
/*       */     //   11: aload_1
/*       */     //   12: <illegal opcode> bbly : (Ljava/lang/Object;Lme/levansj01/hc;)V
/*       */     //   17: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #11899	-> 0
/*       */     //   #19102	-> 7
/*       */     //   #20453	-> 17
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	18	0	bruy	Lme/levansj01/dc;
/*       */     //   0	18	1	bruz	Lme/levansj01/hc;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	18	1	bruz	Lme/levansj01/hc<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setAlerts(boolean bsht) {
/* 11963 */     this.alerts = bsht;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handleOut(ed brut) {
/*       */     // Byte code:
/*       */     //   0: aload_1
/*       */     //   1: <illegal opcode> bblk : (Ljava/lang/Object;)Z
/*       */     //   6: ifne -> 12
/*       */     //   9: goto -> 126
/*       */     //   12: <illegal opcode> bbll : ()J
/*       */     //   17: lstore_2
/*       */     //   18: aload_1
/*       */     //   19: <illegal opcode> bblm : (Ljava/lang/Object;)S
/*       */     //   24: istore #4
/*       */     //   26: aload_0
/*       */     //   27: getfield transactionMap : Ljava/util/Map;
/*       */     //   30: iload #4
/*       */     //   32: <illegal opcode> bbln : (S)Ljava/lang/Short;
/*       */     //   37: aload_0
/*       */     //   38: getfield nextTransaction : Lme/levansj01/df;
/*       */     //   41: <illegal opcode> bblo : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   46: pop
/*       */     //   47: aload_0
/*       */     //   48: getfield nextTransaction : Lme/levansj01/df;
/*       */     //   51: iload #4
/*       */     //   53: lload_2
/*       */     //   54: <illegal opcode> bblp : (Ljava/lang/Object;SJ)V
/*       */     //   59: aload_0
/*       */     //   60: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   63: getstatic me/levansj01/ij.LAST_SENT_TRANSACTION : Lme/levansj01/ij;
/*       */     //   66: getstatic me/levansj01/ij.TOTAL : Lme/levansj01/ij;
/*       */     //   69: <illegal opcode> bblq : (Ljava/lang/Object;Lme/levansj01/ij;Lme/levansj01/ij;)V
/*       */     //   74: aload_0
/*       */     //   75: sipush #1326
/*       */     //   78: sipush #1327
/*       */     //   81: istore #6
/*       */     //   83: istore #5
/*       */     //   85: iload #5
/*       */     //   87: iload #6
/*       */     //   89: ior
/*       */     //   90: iload #5
/*       */     //   92: iconst_m1
/*       */     //   93: ixor
/*       */     //   94: iload #6
/*       */     //   96: iconst_m1
/*       */     //   97: ixor
/*       */     //   98: ior
/*       */     //   99: iand
/*       */     //   100: putfield sentTransaction : Z
/*       */     //   103: aload_0
/*       */     //   104: aload_0
/*       */     //   105: getfield nextTransaction : Lme/levansj01/df;
/*       */     //   108: putfield lastTransaction : Lme/levansj01/df;
/*       */     //   111: aload_0
/*       */     //   112: new me/levansj01/df
/*       */     //   115: dup
/*       */     //   116: aload_0
/*       */     //   117: getfield lastTransaction : Lme/levansj01/df;
/*       */     //   120: invokespecial <init> : (Lme/levansj01/df;)V
/*       */     //   123: putfield nextTransaction : Lme/levansj01/df;
/*       */     //   126: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #12264	-> 0
/*       */     //   #3082	-> 12
/*       */     //   #35250	-> 18
/*       */     //   #40547	-> 26
/*       */     //   #17365	-> 47
/*       */     //   #34410	-> 59
/*       */     //   #45730	-> 74
/*       */     //   #44465	-> 103
/*       */     //   #63619	-> 111
/*       */     //   #47236	-> 126
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	127	1	brut	Lme/levansj01/ed;
/*       */     //   18	108	2	bruq	J
/*       */     //   26	100	4	brur	S
/*       */     //   0	127	0	brus	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setSuffocationTicks(int bsif) {
/* 12356 */     this.suffocationTicks = bsif;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   public Queue<Runnable> getNextTrans() {
/* 12362 */     return this.nextTrans;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public double getLastVelY() {
/* 12458 */     return this.lastVelY;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public is getSpawnLocation() {
/* 12966 */     return this.spawnLocation;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getClientAirTicks() {
/* 13268 */     return this.clientAirTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isFocused(ha bryx) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield focus : Lme/levansj01/j;
/*       */     //   4: ifnull -> 44
/*       */     //   7: aload_0
/*       */     //   8: getfield focus : Lme/levansj01/j;
/*       */     //   11: aload_1
/*       */     //   12: <illegal opcode> bbsg : (Ljava/lang/Object;)Lme/levansj01/j;
/*       */     //   17: if_acmpeq -> 23
/*       */     //   20: goto -> 67
/*       */     //   23: aload_1
/*       */     //   24: <illegal opcode> bbsh : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   29: aload_0
/*       */     //   30: getfield focusSubType : Ljava/lang/String;
/*       */     //   33: <illegal opcode> bbsi : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   38: ifne -> 44
/*       */     //   41: goto -> 67
/*       */     //   44: ldc2_w 1197550668693373240
/*       */     //   47: l2i
/*       */     //   48: sipush #313
/*       */     //   51: istore_3
/*       */     //   52: istore_2
/*       */     //   53: iload_2
/*       */     //   54: iload_3
/*       */     //   55: iconst_m1
/*       */     //   56: ixor
/*       */     //   57: iand
/*       */     //   58: iload_2
/*       */     //   59: iconst_m1
/*       */     //   60: ixor
/*       */     //   61: iload_3
/*       */     //   62: iand
/*       */     //   63: ior
/*       */     //   64: goto -> 83
/*       */     //   67: ldc2_w -4144491974323328896
/*       */     //   70: l2i
/*       */     //   71: sipush #10368
/*       */     //   74: istore_3
/*       */     //   75: istore_2
/*       */     //   76: iload_2
/*       */     //   77: iload_3
/*       */     //   78: ior
/*       */     //   79: iload_2
/*       */     //   80: iload_3
/*       */     //   81: iand
/*       */     //   82: isub
/*       */     //   83: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #13296	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	84	0	bryw	Lme/levansj01/dc;
/*       */     //   0	84	1	bryx	Lme/levansj01/ha;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public UUID getUuid() {
/* 13818 */     return this.uuid;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public lh<Boolean> getShouldHaveReceivedPing() {
/* 13837 */     return this.shouldHaveReceivedPing;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Map<Integer, ka<eb>> getRecentMoveMap() {
/* 14162 */     return this.recentMoveMap;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public boolean hasFast() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: aload_0
/*       */     //   2: getfield lastFlying : J
/*       */     //   5: <illegal opcode> bbud : (Ljava/lang/Object;J)Z
/*       */     //   10: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #14621	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	11	0	bsab	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getFallDamage() {
/* 14635 */     return this.fallDamage;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getPingTicks() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield pingTicks : Lme/levansj01/lh;
/*       */     //   4: <illegal opcode> bbtu : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   9: checkcast java/lang/Integer
/*       */     //   12: <illegal opcode> bbtv : (Ljava/lang/Object;)I
/*       */     //   17: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #14649	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	18	0	brzs	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getHorizontalSpeedTicks() {
/* 14676 */     return this.horizontalSpeedTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public nq getLastLastLocation2() {
/* 14936 */     return this.lastLastLocation2;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isVehicle() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   4: getstatic me/levansj01/ij.VEHICLE : Lme/levansj01/ij;
/*       */     //   7: <illegal opcode> bbvr : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   12: aload_0
/*       */     //   13: <illegal opcode> bbvs : (Ljava/lang/Object;)I
/*       */     //   18: sipush #22599
/*       */     //   21: ldc2_w -5875714744979007419
/*       */     //   24: l2i
/*       */     //   25: istore_2
/*       */     //   26: istore_1
/*       */     //   27: iload_1
/*       */     //   28: iload_2
/*       */     //   29: ior
/*       */     //   30: iload_1
/*       */     //   31: iconst_m1
/*       */     //   32: ixor
/*       */     //   33: iload_2
/*       */     //   34: iconst_m1
/*       */     //   35: ixor
/*       */     //   36: ior
/*       */     //   37: iand
/*       */     //   38: istore_2
/*       */     //   39: istore_1
/*       */     //   40: iload_1
/*       */     //   41: iload_2
/*       */     //   42: ior
/*       */     //   43: iload_1
/*       */     //   44: iload_2
/*       */     //   45: iand
/*       */     //   46: iadd
/*       */     //   47: ldc2_w -3976609888930339242
/*       */     //   50: l2i
/*       */     //   51: sipush #26196
/*       */     //   54: istore_2
/*       */     //   55: istore_1
/*       */     //   56: iload_1
/*       */     //   57: iload_2
/*       */     //   58: ior
/*       */     //   59: iload_1
/*       */     //   60: iload_2
/*       */     //   61: iand
/*       */     //   62: iconst_m1
/*       */     //   63: ixor
/*       */     //   64: iand
/*       */     //   65: imul
/*       */     //   66: if_icmple -> 72
/*       */     //   69: goto -> 93
/*       */     //   72: ldc2_w -3994886190250561839
/*       */     //   75: l2i
/*       */     //   76: sipush #28368
/*       */     //   79: istore_2
/*       */     //   80: istore_1
/*       */     //   81: iload_1
/*       */     //   82: iload_2
/*       */     //   83: ior
/*       */     //   84: iload_1
/*       */     //   85: iload_2
/*       */     //   86: iand
/*       */     //   87: iconst_m1
/*       */     //   88: ixor
/*       */     //   89: iand
/*       */     //   90: goto -> 113
/*       */     //   93: sipush #30284
/*       */     //   96: ldc2_w -4512952064686393780
/*       */     //   99: l2i
/*       */     //   100: istore_2
/*       */     //   101: istore_1
/*       */     //   102: iload_1
/*       */     //   103: iload_2
/*       */     //   104: iconst_m1
/*       */     //   105: ixor
/*       */     //   106: iand
/*       */     //   107: iload_1
/*       */     //   108: iconst_m1
/*       */     //   109: ixor
/*       */     //   110: iload_2
/*       */     //   111: iand
/*       */     //   112: ior
/*       */     //   113: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #15397	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	114	0	bsbg	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isPlacing() {
/* 15641 */     return this.placing;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setSpoofBan(boolean bshn) {
/* 15728 */     this.spoofBan = bshn;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getLastFace() {
/* 15776 */     return this.lastFace;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public String getInfo() {
/*       */     // Byte code:
/*       */     //   0: new java/lang/StringBuilder
/*       */     //   3: dup
/*       */     //   4: invokespecial <init> : ()V
/*       */     //   7: getstatic me/levansj01/verus/VerusPlugin.COLOR : Lnet/md_5/bungee/api/ChatColor;
/*       */     //   10: <illegal opcode> bbxa : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   15: aload_0
/*       */     //   16: getfield name : Ljava/lang/String;
/*       */     //   19: <illegal opcode> bbxb : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   24: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   27: bipush #115
/*       */     //   29: ldc2_w 3142760224843104316
/*       */     //   32: l2i
/*       */     //   33: istore #10
/*       */     //   35: istore #9
/*       */     //   37: iload #9
/*       */     //   39: iload #10
/*       */     //   41: ior
/*       */     //   42: iload #9
/*       */     //   44: iload #10
/*       */     //   46: iand
/*       */     //   47: iconst_m1
/*       */     //   48: ixor
/*       */     //   49: iand
/*       */     //   50: aaload
/*       */     //   51: <illegal opcode> bbxc : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   56: <illegal opcode> bbxd : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   61: astore #4
/*       */     //   63: aload_0
/*       */     //   64: getfield version : Lme/levansj01/kb;
/*       */     //   67: ifnull -> 235
/*       */     //   70: new java/lang/StringBuilder
/*       */     //   73: dup
/*       */     //   74: invokespecial <init> : ()V
/*       */     //   77: aload #4
/*       */     //   79: <illegal opcode> bbxe : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   84: getstatic org/bukkit/ChatColor.GRAY : Lorg/bukkit/ChatColor;
/*       */     //   87: <illegal opcode> bbxf : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   92: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   95: sipush #32696
/*       */     //   98: sipush #32744
/*       */     //   101: istore #10
/*       */     //   103: istore #9
/*       */     //   105: iload #9
/*       */     //   107: iload #10
/*       */     //   109: iconst_m1
/*       */     //   110: ixor
/*       */     //   111: iand
/*       */     //   112: iload #9
/*       */     //   114: iconst_m1
/*       */     //   115: ixor
/*       */     //   116: iload #10
/*       */     //   118: iand
/*       */     //   119: ior
/*       */     //   120: aaload
/*       */     //   121: <illegal opcode> bbxg : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   126: getstatic me/levansj01/verus/VerusPlugin.COLOR : Lnet/md_5/bungee/api/ChatColor;
/*       */     //   129: <illegal opcode> bbxh : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   134: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   137: sipush #28636
/*       */     //   140: istore #8
/*       */     //   142: iload #8
/*       */     //   144: ldc2_w 937206200934625165
/*       */     //   147: l2i
/*       */     //   148: istore #10
/*       */     //   150: istore #9
/*       */     //   152: iload #9
/*       */     //   154: iload #10
/*       */     //   156: ior
/*       */     //   157: iload #9
/*       */     //   159: iconst_m1
/*       */     //   160: ixor
/*       */     //   161: iload #10
/*       */     //   163: iconst_m1
/*       */     //   164: ixor
/*       */     //   165: ior
/*       */     //   166: iand
/*       */     //   167: aaload
/*       */     //   168: <illegal opcode> bbxi : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   173: getstatic org/bukkit/ChatColor.WHITE : Lorg/bukkit/ChatColor;
/*       */     //   176: <illegal opcode> bbxj : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   181: aload_0
/*       */     //   182: getfield version : Lme/levansj01/kb;
/*       */     //   185: <illegal opcode> bbxk : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   190: <illegal opcode> bbxl : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   195: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   198: ldc2_w 5676264950608173257
/*       */     //   201: l2i
/*       */     //   202: sipush #6299
/*       */     //   205: istore #10
/*       */     //   207: istore #9
/*       */     //   209: iload #9
/*       */     //   211: iload #10
/*       */     //   213: ior
/*       */     //   214: iload #9
/*       */     //   216: iload #10
/*       */     //   218: iand
/*       */     //   219: iconst_m1
/*       */     //   220: ixor
/*       */     //   221: iand
/*       */     //   222: aaload
/*       */     //   223: <illegal opcode> bbxm : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   228: <illegal opcode> bbxn : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   233: astore #4
/*       */     //   235: aload_0
/*       */     //   236: getfield clientData : Lme/levansj01/nc;
/*       */     //   239: <illegal opcode> bbxo : (Ljava/lang/Object;)Lme/levansj01/kt;
/*       */     //   244: dup
/*       */     //   245: astore_3
/*       */     //   246: <illegal opcode> bbxp : (Ljava/lang/Object;)Z
/*       */     //   251: ifeq -> 257
/*       */     //   254: goto -> 422
/*       */     //   257: new java/lang/StringBuilder
/*       */     //   260: dup
/*       */     //   261: invokespecial <init> : ()V
/*       */     //   264: aload #4
/*       */     //   266: <illegal opcode> bbxq : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   271: getstatic org/bukkit/ChatColor.GRAY : Lorg/bukkit/ChatColor;
/*       */     //   274: <illegal opcode> bbxr : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   279: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   282: sipush #23040
/*       */     //   285: ldc2_w 5650500296497977939
/*       */     //   288: l2i
/*       */     //   289: istore #10
/*       */     //   291: istore #9
/*       */     //   293: iload #9
/*       */     //   295: iload #10
/*       */     //   297: ior
/*       */     //   298: iload #9
/*       */     //   300: iconst_m1
/*       */     //   301: ixor
/*       */     //   302: iload #10
/*       */     //   304: iconst_m1
/*       */     //   305: ixor
/*       */     //   306: ior
/*       */     //   307: iand
/*       */     //   308: aaload
/*       */     //   309: <illegal opcode> bbxs : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   314: getstatic me/levansj01/verus/VerusPlugin.COLOR : Lnet/md_5/bungee/api/ChatColor;
/*       */     //   317: <illegal opcode> bbxt : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   322: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   325: sipush #23124
/*       */     //   328: istore #8
/*       */     //   330: ldc2_w 6689755912522979840
/*       */     //   333: l2i
/*       */     //   334: iload #8
/*       */     //   336: istore #10
/*       */     //   338: istore #9
/*       */     //   340: iload #9
/*       */     //   342: iload #10
/*       */     //   344: iconst_m1
/*       */     //   345: ixor
/*       */     //   346: iand
/*       */     //   347: iload #9
/*       */     //   349: iconst_m1
/*       */     //   350: ixor
/*       */     //   351: iload #10
/*       */     //   353: iand
/*       */     //   354: ior
/*       */     //   355: aaload
/*       */     //   356: <illegal opcode> bbxu : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   361: getstatic org/bukkit/ChatColor.WHITE : Lorg/bukkit/ChatColor;
/*       */     //   364: <illegal opcode> bbxv : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   369: aload_3
/*       */     //   370: <illegal opcode> bbxw : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   375: <illegal opcode> bbxx : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   380: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   383: sipush #9975
/*       */     //   386: istore #8
/*       */     //   388: iload #8
/*       */     //   390: ldc2_w 4690244599518602914
/*       */     //   393: l2i
/*       */     //   394: istore #10
/*       */     //   396: istore #9
/*       */     //   398: iload #9
/*       */     //   400: iload #10
/*       */     //   402: ior
/*       */     //   403: iload #9
/*       */     //   405: iload #10
/*       */     //   407: iand
/*       */     //   408: isub
/*       */     //   409: aaload
/*       */     //   410: <illegal opcode> bbxy : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   415: <illegal opcode> bbxz : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   420: astore #4
/*       */     //   422: aload_0
/*       */     //   423: getfield clientData : Lme/levansj01/nc;
/*       */     //   426: <illegal opcode> bbya : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   431: dup
/*       */     //   432: astore_2
/*       */     //   433: ifnull -> 643
/*       */     //   436: aload_2
/*       */     //   437: <illegal opcode> bbyb : (Ljava/lang/Object;)Z
/*       */     //   442: ifeq -> 448
/*       */     //   445: goto -> 643
/*       */     //   448: aload_2
/*       */     //   449: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   452: sipush #3815
/*       */     //   455: sipush #3761
/*       */     //   458: istore #10
/*       */     //   460: istore #9
/*       */     //   462: iload #9
/*       */     //   464: iload #10
/*       */     //   466: ior
/*       */     //   467: iload #9
/*       */     //   469: iload #10
/*       */     //   471: iand
/*       */     //   472: isub
/*       */     //   473: aaload
/*       */     //   474: <illegal opcode> bbyc : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   479: ifeq -> 485
/*       */     //   482: goto -> 643
/*       */     //   485: new java/lang/StringBuilder
/*       */     //   488: dup
/*       */     //   489: invokespecial <init> : ()V
/*       */     //   492: aload #4
/*       */     //   494: <illegal opcode> bbyd : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   499: getstatic org/bukkit/ChatColor.GRAY : Lorg/bukkit/ChatColor;
/*       */     //   502: <illegal opcode> bbye : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   507: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   510: sipush #28773
/*       */     //   513: istore #8
/*       */     //   515: ldc2_w -2621463998129737678
/*       */     //   518: l2i
/*       */     //   519: iload #8
/*       */     //   521: istore #10
/*       */     //   523: istore #9
/*       */     //   525: iload #9
/*       */     //   527: iload #10
/*       */     //   529: ior
/*       */     //   530: iload #9
/*       */     //   532: iload #10
/*       */     //   534: iand
/*       */     //   535: iconst_m1
/*       */     //   536: ixor
/*       */     //   537: iand
/*       */     //   538: aaload
/*       */     //   539: <illegal opcode> bbyf : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   544: getstatic me/levansj01/verus/VerusPlugin.COLOR : Lnet/md_5/bungee/api/ChatColor;
/*       */     //   547: <illegal opcode> bbyg : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   552: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   555: sipush #30037
/*       */     //   558: ldc2_w -8121683234321828595
/*       */     //   561: l2i
/*       */     //   562: istore #10
/*       */     //   564: istore #9
/*       */     //   566: iload #9
/*       */     //   568: iload #10
/*       */     //   570: ior
/*       */     //   571: iload #9
/*       */     //   573: iconst_m1
/*       */     //   574: ixor
/*       */     //   575: iload #10
/*       */     //   577: iconst_m1
/*       */     //   578: ixor
/*       */     //   579: ior
/*       */     //   580: iand
/*       */     //   581: aaload
/*       */     //   582: <illegal opcode> bbyh : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   587: getstatic org/bukkit/ChatColor.WHITE : Lorg/bukkit/ChatColor;
/*       */     //   590: <illegal opcode> bbyi : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   595: aload_2
/*       */     //   596: <illegal opcode> bbyj : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   601: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   604: ldc2_w -1081848368612752722
/*       */     //   607: l2i
/*       */     //   608: sipush #17143
/*       */     //   611: istore #10
/*       */     //   613: istore #9
/*       */     //   615: iload #9
/*       */     //   617: iload #10
/*       */     //   619: iconst_m1
/*       */     //   620: ixor
/*       */     //   621: iand
/*       */     //   622: iload #9
/*       */     //   624: iconst_m1
/*       */     //   625: ixor
/*       */     //   626: iload #10
/*       */     //   628: iand
/*       */     //   629: ior
/*       */     //   630: aaload
/*       */     //   631: <illegal opcode> bbyk : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   636: <illegal opcode> bbyl : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   641: astore #4
/*       */     //   643: aload_0
/*       */     //   644: getfield enabled : Z
/*       */     //   647: ifne -> 653
/*       */     //   650: goto -> 1034
/*       */     //   653: new java/lang/StringBuilder
/*       */     //   656: dup
/*       */     //   657: invokespecial <init> : ()V
/*       */     //   660: aload #4
/*       */     //   662: <illegal opcode> bbym : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   667: getstatic org/bukkit/ChatColor.GRAY : Lorg/bukkit/ChatColor;
/*       */     //   670: <illegal opcode> bbyn : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   675: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   678: sipush #32210
/*       */     //   681: istore #8
/*       */     //   683: iload #8
/*       */     //   685: ldc2_w -346597611609752184
/*       */     //   688: l2i
/*       */     //   689: istore #10
/*       */     //   691: istore #9
/*       */     //   693: iload #9
/*       */     //   695: iload #10
/*       */     //   697: ior
/*       */     //   698: iload #9
/*       */     //   700: iload #10
/*       */     //   702: iand
/*       */     //   703: iconst_m1
/*       */     //   704: ixor
/*       */     //   705: iand
/*       */     //   706: aaload
/*       */     //   707: <illegal opcode> bbyo : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   712: getstatic me/levansj01/verus/VerusPlugin.COLOR : Lnet/md_5/bungee/api/ChatColor;
/*       */     //   715: <illegal opcode> bbyp : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   720: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   723: sipush #8852
/*       */     //   726: istore #8
/*       */     //   728: ldc2_w 5714197285722661583
/*       */     //   731: l2i
/*       */     //   732: iload #8
/*       */     //   734: istore #10
/*       */     //   736: istore #9
/*       */     //   738: iload #9
/*       */     //   740: iload #10
/*       */     //   742: ior
/*       */     //   743: iload #9
/*       */     //   745: iconst_m1
/*       */     //   746: ixor
/*       */     //   747: iload #10
/*       */     //   749: iconst_m1
/*       */     //   750: ixor
/*       */     //   751: ior
/*       */     //   752: iand
/*       */     //   753: aaload
/*       */     //   754: <illegal opcode> bbyq : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   759: getstatic org/bukkit/ChatColor.WHITE : Lorg/bukkit/ChatColor;
/*       */     //   762: <illegal opcode> bbyr : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   767: aload_0
/*       */     //   768: <illegal opcode> bbys : (Ljava/lang/Object;)I
/*       */     //   773: <illegal opcode> bbyt : (Ljava/lang/Object;I)Ljava/lang/StringBuilder;
/*       */     //   778: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   781: sipush #3783
/*       */     //   784: istore #8
/*       */     //   786: iload #8
/*       */     //   788: sipush #3739
/*       */     //   791: istore #10
/*       */     //   793: istore #9
/*       */     //   795: iload #9
/*       */     //   797: iload #10
/*       */     //   799: ior
/*       */     //   800: iload #9
/*       */     //   802: iload #10
/*       */     //   804: iand
/*       */     //   805: isub
/*       */     //   806: aaload
/*       */     //   807: <illegal opcode> bbyu : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   812: <illegal opcode> bbyv : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   817: astore #4
/*       */     //   819: new java/lang/StringBuilder
/*       */     //   822: dup
/*       */     //   823: invokespecial <init> : ()V
/*       */     //   826: aload #4
/*       */     //   828: <illegal opcode> bbyw : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   833: getstatic org/bukkit/ChatColor.GRAY : Lorg/bukkit/ChatColor;
/*       */     //   836: <illegal opcode> bbyx : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   841: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   844: sipush #17082
/*       */     //   847: ldc2_w 7814283508595049191
/*       */     //   850: l2i
/*       */     //   851: istore #10
/*       */     //   853: istore #9
/*       */     //   855: iload #9
/*       */     //   857: iload #10
/*       */     //   859: ior
/*       */     //   860: iload #9
/*       */     //   862: iload #10
/*       */     //   864: iand
/*       */     //   865: iconst_m1
/*       */     //   866: ixor
/*       */     //   867: iand
/*       */     //   868: aaload
/*       */     //   869: <illegal opcode> bbyy : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   874: getstatic me/levansj01/verus/VerusPlugin.COLOR : Lnet/md_5/bungee/api/ChatColor;
/*       */     //   877: <illegal opcode> bbyz : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   882: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   885: sipush #6011
/*       */     //   888: sipush #5925
/*       */     //   891: istore #10
/*       */     //   893: istore #9
/*       */     //   895: iload #9
/*       */     //   897: iload #10
/*       */     //   899: ior
/*       */     //   900: iload #9
/*       */     //   902: iload #10
/*       */     //   904: iand
/*       */     //   905: isub
/*       */     //   906: aaload
/*       */     //   907: <illegal opcode> bbza : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   912: getstatic org/bukkit/ChatColor.WHITE : Lorg/bukkit/ChatColor;
/*       */     //   915: <illegal opcode> bbzb : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   920: aload_0
/*       */     //   921: <illegal opcode> bbzc : (Ljava/lang/Object;)Ljava/lang/Integer;
/*       */     //   926: <illegal opcode> bbzd : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   931: getstatic org/bukkit/ChatColor.GRAY : Lorg/bukkit/ChatColor;
/*       */     //   934: <illegal opcode> bbze : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   939: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   942: sipush #4918
/*       */     //   945: ldc2_w -9160784308833610903
/*       */     //   948: l2i
/*       */     //   949: istore #10
/*       */     //   951: istore #9
/*       */     //   953: iload #9
/*       */     //   955: iload #10
/*       */     //   957: iconst_m1
/*       */     //   958: ixor
/*       */     //   959: iand
/*       */     //   960: iload #9
/*       */     //   962: iconst_m1
/*       */     //   963: ixor
/*       */     //   964: iload #10
/*       */     //   966: iand
/*       */     //   967: ior
/*       */     //   968: aaload
/*       */     //   969: <illegal opcode> bbzf : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   974: aload_0
/*       */     //   975: <illegal opcode> bbzg : (Ljava/lang/Object;)Lme/levansj01/mu;
/*       */     //   980: <illegal opcode> bbzh : (Ljava/lang/Object;)I
/*       */     //   985: <illegal opcode> bbzi : (Ljava/lang/Object;I)Ljava/lang/StringBuilder;
/*       */     //   990: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   993: sipush #19856
/*       */     //   996: istore #8
/*       */     //   998: iload #8
/*       */     //   1000: ldc2_w 8667749108661898736
/*       */     //   1003: l2i
/*       */     //   1004: istore #10
/*       */     //   1006: istore #9
/*       */     //   1008: iload #9
/*       */     //   1010: iload #10
/*       */     //   1012: ior
/*       */     //   1013: iload #9
/*       */     //   1015: iload #10
/*       */     //   1017: iand
/*       */     //   1018: iconst_m1
/*       */     //   1019: ixor
/*       */     //   1020: iand
/*       */     //   1021: aaload
/*       */     //   1022: <illegal opcode> bbzj : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1027: <illegal opcode> bbzk : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   1032: astore #4
/*       */     //   1034: aload_0
/*       */     //   1035: getfield effects : Lme/levansj01/lh;
/*       */     //   1038: <illegal opcode> bbzl : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   1043: checkcast [Lorg/bukkit/potion/PotionEffect;
/*       */     //   1046: checkcast [Lorg/bukkit/potion/PotionEffect;
/*       */     //   1049: dup
/*       */     //   1050: astore_1
/*       */     //   1051: arraylength
/*       */     //   1052: ifgt -> 1058
/*       */     //   1055: goto -> 1276
/*       */     //   1058: new java/lang/StringBuilder
/*       */     //   1061: dup
/*       */     //   1062: invokespecial <init> : ()V
/*       */     //   1065: aload #4
/*       */     //   1067: <illegal opcode> bbzm : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1072: getstatic org/bukkit/ChatColor.GRAY : Lorg/bukkit/ChatColor;
/*       */     //   1075: <illegal opcode> bbzn : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   1080: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   1083: sipush #13976
/*       */     //   1086: istore #8
/*       */     //   1088: iload #8
/*       */     //   1090: ldc2_w -1231104684081531143
/*       */     //   1093: l2i
/*       */     //   1094: istore #10
/*       */     //   1096: istore #9
/*       */     //   1098: iload #9
/*       */     //   1100: iload #10
/*       */     //   1102: iconst_m1
/*       */     //   1103: ixor
/*       */     //   1104: iand
/*       */     //   1105: iload #9
/*       */     //   1107: iconst_m1
/*       */     //   1108: ixor
/*       */     //   1109: iload #10
/*       */     //   1111: iand
/*       */     //   1112: ior
/*       */     //   1113: aaload
/*       */     //   1114: <illegal opcode> bbzo : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1119: getstatic me/levansj01/verus/VerusPlugin.COLOR : Lnet/md_5/bungee/api/ChatColor;
/*       */     //   1122: <illegal opcode> bbzp : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   1127: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   1130: sipush #13231
/*       */     //   1133: istore #8
/*       */     //   1135: iload #8
/*       */     //   1137: sipush #13261
/*       */     //   1140: istore #10
/*       */     //   1142: istore #9
/*       */     //   1144: iload #9
/*       */     //   1146: iload #10
/*       */     //   1148: iconst_m1
/*       */     //   1149: ixor
/*       */     //   1150: iand
/*       */     //   1151: iload #9
/*       */     //   1153: iconst_m1
/*       */     //   1154: ixor
/*       */     //   1155: iload #10
/*       */     //   1157: iand
/*       */     //   1158: ior
/*       */     //   1159: aaload
/*       */     //   1160: <illegal opcode> bbzq : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1165: getstatic org/bukkit/ChatColor.WHITE : Lorg/bukkit/ChatColor;
/*       */     //   1168: <illegal opcode> bbzr : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   1173: aload_1
/*       */     //   1174: <illegal opcode> bbzs : ([Ljava/lang/Object;)Ljava/util/stream/Stream;
/*       */     //   1179: <illegal opcode> apply : ()Ljava/util/function/Function;
/*       */     //   1184: <illegal opcode> bbzt : (Ljava/lang/Object;Ljava/util/function/Function;)Ljava/util/stream/Stream;
/*       */     //   1189: new java/lang/StringBuilder
/*       */     //   1192: dup
/*       */     //   1193: invokespecial <init> : ()V
/*       */     //   1196: getstatic org/bukkit/ChatColor.GRAY : Lorg/bukkit/ChatColor;
/*       */     //   1199: <illegal opcode> bbzu : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   1204: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   1207: sipush #13669
/*       */     //   1210: ldc2_w -2153690442678389498
/*       */     //   1213: l2i
/*       */     //   1214: istore #10
/*       */     //   1216: istore #9
/*       */     //   1218: iload #9
/*       */     //   1220: iload #10
/*       */     //   1222: ior
/*       */     //   1223: iload #9
/*       */     //   1225: iload #10
/*       */     //   1227: iand
/*       */     //   1228: iconst_m1
/*       */     //   1229: ixor
/*       */     //   1230: iand
/*       */     //   1231: aaload
/*       */     //   1232: <illegal opcode> bbzv : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1237: getstatic org/bukkit/ChatColor.WHITE : Lorg/bukkit/ChatColor;
/*       */     //   1240: <illegal opcode> bbzw : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   1245: <illegal opcode> bbzx : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   1250: <illegal opcode> bbzy : (Ljava/lang/CharSequence;)Ljava/util/stream/Collector;
/*       */     //   1255: <illegal opcode> bbzz : (Ljava/lang/Object;Ljava/util/stream/Collector;)Ljava/lang/Object;
/*       */     //   1260: checkcast java/lang/String
/*       */     //   1263: <illegal opcode> bcaa : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1268: <illegal opcode> bcab : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   1273: goto -> 1429
/*       */     //   1276: new java/lang/StringBuilder
/*       */     //   1279: dup
/*       */     //   1280: invokespecial <init> : ()V
/*       */     //   1283: aload #4
/*       */     //   1285: <illegal opcode> bcac : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1290: getstatic org/bukkit/ChatColor.GRAY : Lorg/bukkit/ChatColor;
/*       */     //   1293: <illegal opcode> bcad : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   1298: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   1301: sipush #20779
/*       */     //   1304: istore #8
/*       */     //   1306: iload #8
/*       */     //   1308: sipush #20815
/*       */     //   1311: istore #10
/*       */     //   1313: istore #9
/*       */     //   1315: iload #9
/*       */     //   1317: iload #10
/*       */     //   1319: iconst_m1
/*       */     //   1320: ixor
/*       */     //   1321: iand
/*       */     //   1322: iload #9
/*       */     //   1324: iconst_m1
/*       */     //   1325: ixor
/*       */     //   1326: iload #10
/*       */     //   1328: iand
/*       */     //   1329: ior
/*       */     //   1330: aaload
/*       */     //   1331: <illegal opcode> bcae : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1336: getstatic me/levansj01/verus/VerusPlugin.COLOR : Lnet/md_5/bungee/api/ChatColor;
/*       */     //   1339: <illegal opcode> bcaf : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   1344: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   1347: sipush #15887
/*       */     //   1350: istore #8
/*       */     //   1352: ldc2_w -5576645194222125462
/*       */     //   1355: l2i
/*       */     //   1356: iload #8
/*       */     //   1358: istore #10
/*       */     //   1360: istore #9
/*       */     //   1362: iload #9
/*       */     //   1364: iload #10
/*       */     //   1366: ior
/*       */     //   1367: iload #9
/*       */     //   1369: iload #10
/*       */     //   1371: iand
/*       */     //   1372: iconst_m1
/*       */     //   1373: ixor
/*       */     //   1374: iand
/*       */     //   1375: aaload
/*       */     //   1376: <illegal opcode> bcag : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1381: getstatic org/bukkit/ChatColor.WHITE : Lorg/bukkit/ChatColor;
/*       */     //   1384: <illegal opcode> bcah : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*       */     //   1389: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   1392: sipush #28078
/*       */     //   1395: istore #8
/*       */     //   1397: iload #8
/*       */     //   1399: ldc2_w 4035844106486836680
/*       */     //   1402: l2i
/*       */     //   1403: istore #10
/*       */     //   1405: istore #9
/*       */     //   1407: iload #9
/*       */     //   1409: iload #10
/*       */     //   1411: ior
/*       */     //   1412: iload #9
/*       */     //   1414: iload #10
/*       */     //   1416: iand
/*       */     //   1417: isub
/*       */     //   1418: aaload
/*       */     //   1419: <illegal opcode> bcai : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   1424: <illegal opcode> bcaj : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   1429: astore #4
/*       */     //   1431: aload #4
/*       */     //   1433: areturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #16050	-> 0
/*       */     //   #52343	-> 63
/*       */     //   #24391	-> 70
/*       */     //   #63441	-> 235
/*       */     //   #15444	-> 257
/*       */     //   #65440	-> 422
/*       */     //   #45748	-> 485
/*       */     //   #22445	-> 643
/*       */     //   #16735	-> 653
/*       */     //   #53850	-> 819
/*       */     //   #41684	-> 1034
/*       */     //   #42822	-> 1431
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	1434	0	bsce	Lme/levansj01/dc;
/*       */     //   1051	383	1	bscf	[Lorg/bukkit/potion/PotionEffect;
/*       */     //   433	1001	2	bscg	Ljava/lang/String;
/*       */     //   246	1188	3	bsch	Lme/levansj01/kt;
/*       */     //   63	1371	4	bsci	Ljava/lang/String;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getLastInventoryOutTick() {
/* 16240 */     return this.lastInventoryOutTick;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public gk getMetadataHandler() {
/* 16483 */     return this.metadataHandler;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(ec<?> brum) {
/*       */     // Byte code:
/*       */     //   0: aload_1
/*       */     //   1: <illegal opcode> bbkj : (Ljava/lang/Object;)Lme/levansj01/jq;
/*       */     //   6: getstatic me/levansj01/jq.ATTACK : Lme/levansj01/jq;
/*       */     //   9: if_acmpeq -> 15
/*       */     //   12: goto -> 324
/*       */     //   15: aload_0
/*       */     //   16: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   19: getstatic me/levansj01/ij.ATTACKS : Lme/levansj01/ij;
/*       */     //   22: <illegal opcode> bbkk : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   27: pop
/*       */     //   28: aload_0
/*       */     //   29: aconst_null
/*       */     //   30: putfield lastAttacked : Lme/levansj01/dc;
/*       */     //   33: aload_0
/*       */     //   34: aload_1
/*       */     //   35: <illegal opcode> bbkl : (Ljava/lang/Object;)I
/*       */     //   40: putfield lastAttackedId : I
/*       */     //   43: aload_1
/*       */     //   44: <illegal opcode> bbkm : (Ljava/lang/Object;)Z
/*       */     //   49: ifne -> 55
/*       */     //   52: goto -> 295
/*       */     //   55: aload_0
/*       */     //   56: aload_1
/*       */     //   57: <illegal opcode> bbkn : (Ljava/lang/Object;)Lme/levansj01/dc;
/*       */     //   62: putfield lastAttacked : Lme/levansj01/dc;
/*       */     //   65: <illegal opcode> bbko : ()Ljava/util/concurrent/ThreadLocalRandom;
/*       */     //   70: astore_2
/*       */     //   71: <illegal opcode> bbkp : ()Lme/levansj01/ne;
/*       */     //   76: astore_3
/*       */     //   77: aload_0
/*       */     //   78: getfield spoofBan : Z
/*       */     //   81: ifne -> 87
/*       */     //   84: goto -> 146
/*       */     //   87: aload_2
/*       */     //   88: <illegal opcode> bbkq : (Ljava/lang/Object;)D
/*       */     //   93: ldc2_w 0.1
/*       */     //   96: dcmpg
/*       */     //   97: iflt -> 103
/*       */     //   100: goto -> 146
/*       */     //   103: aload_3
/*       */     //   104: aload_0
/*       */     //   105: aload_0
/*       */     //   106: getfield spoofBanCheck : Lme/levansj01/ha;
/*       */     //   109: sipush #4640
/*       */     //   112: istore #8
/*       */     //   114: iload #8
/*       */     //   116: sipush #4640
/*       */     //   119: istore #10
/*       */     //   121: istore #9
/*       */     //   123: iload #9
/*       */     //   125: iload #10
/*       */     //   127: ior
/*       */     //   128: iload #9
/*       */     //   130: iconst_m1
/*       */     //   131: ixor
/*       */     //   132: iload #10
/*       */     //   134: iconst_m1
/*       */     //   135: ixor
/*       */     //   136: ior
/*       */     //   137: iand
/*       */     //   138: <illegal opcode> bbkr : (Ljava/lang/Object;Lme/levansj01/dc;Lme/levansj01/ha;Z)V
/*       */     //   143: goto -> 295
/*       */     //   146: aload_0
/*       */     //   147: getfield checkSpoofing : Z
/*       */     //   150: ifne -> 156
/*       */     //   153: goto -> 295
/*       */     //   156: aload_2
/*       */     //   157: <illegal opcode> bbks : (Ljava/lang/Object;)D
/*       */     //   162: ldc2_w 0.25
/*       */     //   165: dcmpg
/*       */     //   166: iflt -> 172
/*       */     //   169: goto -> 295
/*       */     //   172: aload_0
/*       */     //   173: getfield checkSpoofing : Z
/*       */     //   176: ifne -> 182
/*       */     //   179: goto -> 295
/*       */     //   182: aload_0
/*       */     //   183: getfield spoofedAlerts : Ljava/util/Queue;
/*       */     //   186: <illegal opcode> bbkt : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   191: checkcast me/levansj01/em
/*       */     //   194: astore #4
/*       */     //   196: aload #4
/*       */     //   198: ifnonnull -> 238
/*       */     //   201: aload_0
/*       */     //   202: sipush #9358
/*       */     //   205: istore #8
/*       */     //   207: iload #8
/*       */     //   209: ldc2_w -1402910179149732722
/*       */     //   212: l2i
/*       */     //   213: istore #10
/*       */     //   215: istore #9
/*       */     //   217: iload #9
/*       */     //   219: iload #10
/*       */     //   221: ior
/*       */     //   222: iload #9
/*       */     //   224: iconst_m1
/*       */     //   225: ixor
/*       */     //   226: iload #10
/*       */     //   228: iconst_m1
/*       */     //   229: ixor
/*       */     //   230: ior
/*       */     //   231: iand
/*       */     //   232: putfield checkSpoofing : Z
/*       */     //   235: goto -> 172
/*       */     //   238: aload_0
/*       */     //   239: getfield timestamp : J
/*       */     //   242: aload #4
/*       */     //   244: <illegal opcode> bbku : (Ljava/lang/Object;)J
/*       */     //   249: lsub
/*       */     //   250: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
/*       */     //   253: ldc2_w 15
/*       */     //   256: <illegal opcode> bbkv : (Ljava/lang/Object;J)J
/*       */     //   261: lcmp
/*       */     //   262: ifge -> 268
/*       */     //   265: goto -> 271
/*       */     //   268: goto -> 172
/*       */     //   271: aload_3
/*       */     //   272: <illegal opcode> bbkw : (Ljava/lang/Object;)Ljava/util/concurrent/ExecutorService;
/*       */     //   277: aload_0
/*       */     //   278: aload_3
/*       */     //   279: aload #4
/*       */     //   281: <illegal opcode> call : (Lme/levansj01/dc;Lme/levansj01/ne;Lme/levansj01/em;)Ljava/util/concurrent/Callable;
/*       */     //   286: <illegal opcode> bbkx : (Ljava/lang/Object;Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
/*       */     //   291: pop
/*       */     //   292: goto -> 295
/*       */     //   295: aload_0
/*       */     //   296: sipush #27971
/*       */     //   299: sipush #27971
/*       */     //   302: istore #10
/*       */     //   304: istore #9
/*       */     //   306: iload #9
/*       */     //   308: iload #10
/*       */     //   310: ior
/*       */     //   311: iload #9
/*       */     //   313: iconst_m1
/*       */     //   314: ixor
/*       */     //   315: iload #10
/*       */     //   317: iconst_m1
/*       */     //   318: ixor
/*       */     //   319: ior
/*       */     //   320: iand
/*       */     //   321: putfield lastAttackTicks : I
/*       */     //   324: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #16827	-> 0
/*       */     //   #1098	-> 15
/*       */     //   #58973	-> 28
/*       */     //   #13327	-> 33
/*       */     //   #21343	-> 43
/*       */     //   #19834	-> 55
/*       */     //   #7354	-> 65
/*       */     //   #53007	-> 71
/*       */     //   #53329	-> 77
/*       */     //   #9443	-> 103
/*       */     //   #20721	-> 146
/*       */     //   #55595	-> 172
/*       */     //   #57434	-> 182
/*       */     //   #28134	-> 196
/*       */     //   #53987	-> 201
/*       */     //   #37812	-> 235
/*       */     //   #31841	-> 238
/*       */     //   #24757	-> 271
/*       */     //   #4507	-> 292
/*       */     //   #58867	-> 295
/*       */     //   #40872	-> 324
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	325	1	brum	Lme/levansj01/ec;
/*       */     //   0	325	0	brul	Lme/levansj01/dc;
/*       */     //   71	224	2	bruj	Ljava/util/concurrent/ThreadLocalRandom;
/*       */     //   77	218	3	bruk	Lme/levansj01/ne;
/*       */     //   196	99	4	brui	Lme/levansj01/em;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	325	1	brum	Lme/levansj01/ec<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(ia<?> brst) {
/*       */     // Byte code:
/*       */     //   0: aload_1
/*       */     //   1: <illegal opcode> bbcg : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   6: astore_2
/*       */     //   7: <illegal opcode> bbch : ()Ljava/util/Calendar;
/*       */     //   12: astore_3
/*       */     //   13: aload_3
/*       */     //   14: sipush #25217
/*       */     //   17: istore #11
/*       */     //   19: ldc2_w 3277534451755672196
/*       */     //   22: l2i
/*       */     //   23: iload #11
/*       */     //   25: istore #13
/*       */     //   27: istore #12
/*       */     //   29: iload #12
/*       */     //   31: iload #13
/*       */     //   33: ior
/*       */     //   34: iload #12
/*       */     //   36: iload #13
/*       */     //   38: iand
/*       */     //   39: isub
/*       */     //   40: <illegal opcode> bbci : (Ljava/lang/Object;I)I
/*       */     //   45: istore #4
/*       */     //   47: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   50: sipush #16177
/*       */     //   53: istore #11
/*       */     //   55: iload #11
/*       */     //   57: ldc2_w 2588291985722851086
/*       */     //   60: l2i
/*       */     //   61: istore #13
/*       */     //   63: istore #12
/*       */     //   65: iload #12
/*       */     //   67: iload #13
/*       */     //   69: ior
/*       */     //   70: iload #12
/*       */     //   72: iload #13
/*       */     //   74: iand
/*       */     //   75: iconst_m1
/*       */     //   76: ixor
/*       */     //   77: iand
/*       */     //   78: aaload
/*       */     //   79: astore #5
/*       */     //   81: <illegal opcode> bbcj : ()Ljavax/crypto/spec/SecretKeySpec;
/*       */     //   86: new java/lang/StringBuilder
/*       */     //   89: dup
/*       */     //   90: invokespecial <init> : ()V
/*       */     //   93: ldc_w ''
/*       */     //   96: <illegal opcode> bbck : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   101: iload #4
/*       */     //   103: <illegal opcode> bbcl : (Ljava/lang/Object;I)Ljava/lang/StringBuilder;
/*       */     //   108: <illegal opcode> bbcm : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   113: <illegal opcode> bbcn : (Ljava/security/Key;Ljava/lang/String;)Ljava/lang/String;
/*       */     //   118: astore #5
/*       */     //   120: goto -> 125
/*       */     //   123: astore #6
/*       */     //   125: aload_2
/*       */     //   126: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   129: sipush #11668
/*       */     //   132: istore #11
/*       */     //   134: iload #11
/*       */     //   136: ldc2_w 6215639575138676180
/*       */     //   139: l2i
/*       */     //   140: istore #13
/*       */     //   142: istore #12
/*       */     //   144: iload #12
/*       */     //   146: iload #13
/*       */     //   148: ior
/*       */     //   149: iload #12
/*       */     //   151: iload #13
/*       */     //   153: iand
/*       */     //   154: iconst_m1
/*       */     //   155: ixor
/*       */     //   156: iand
/*       */     //   157: aaload
/*       */     //   158: <illegal opcode> bbco : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   163: ifeq -> 169
/*       */     //   166: goto -> 183
/*       */     //   169: aload_2
/*       */     //   170: aload #5
/*       */     //   172: <illegal opcode> bbcp : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   177: ifne -> 183
/*       */     //   180: goto -> 217
/*       */     //   183: aload_0
/*       */     //   184: sipush #327
/*       */     //   187: istore #11
/*       */     //   189: iload #11
/*       */     //   191: ldc2_w -6584917397210136250
/*       */     //   194: l2i
/*       */     //   195: istore #13
/*       */     //   197: istore #12
/*       */     //   199: iload #12
/*       */     //   201: iload #13
/*       */     //   203: ior
/*       */     //   204: iload #12
/*       */     //   206: iload #13
/*       */     //   208: iand
/*       */     //   209: iconst_m1
/*       */     //   210: ixor
/*       */     //   211: iand
/*       */     //   212: <illegal opcode> bbcq : (Ljava/lang/Object;Z)V
/*       */     //   217: aload_2
/*       */     //   218: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   221: sipush #6412
/*       */     //   224: istore #11
/*       */     //   226: iload #11
/*       */     //   228: sipush #6477
/*       */     //   231: istore #13
/*       */     //   233: istore #12
/*       */     //   235: iload #12
/*       */     //   237: iload #13
/*       */     //   239: ior
/*       */     //   240: iload #12
/*       */     //   242: iload #13
/*       */     //   244: iand
/*       */     //   245: isub
/*       */     //   246: aaload
/*       */     //   247: <illegal opcode> bbcr : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   252: ifeq -> 258
/*       */     //   255: goto -> 323
/*       */     //   258: aload_2
/*       */     //   259: new java/lang/StringBuilder
/*       */     //   262: dup
/*       */     //   263: invokespecial <init> : ()V
/*       */     //   266: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   269: sipush #24732
/*       */     //   272: sipush #24798
/*       */     //   275: istore #13
/*       */     //   277: istore #12
/*       */     //   279: iload #12
/*       */     //   281: iload #13
/*       */     //   283: iconst_m1
/*       */     //   284: ixor
/*       */     //   285: iand
/*       */     //   286: iload #12
/*       */     //   288: iconst_m1
/*       */     //   289: ixor
/*       */     //   290: iload #13
/*       */     //   292: iand
/*       */     //   293: ior
/*       */     //   294: aaload
/*       */     //   295: <illegal opcode> bbcs : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   300: aload #5
/*       */     //   302: <illegal opcode> bbct : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   307: <illegal opcode> bbcu : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   312: <illegal opcode> bbcv : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   317: ifne -> 323
/*       */     //   320: goto -> 405
/*       */     //   323: <illegal opcode> bbcw : ()Lorg/bukkit/command/ConsoleCommandSender;
/*       */     //   328: astore #6
/*       */     //   330: <illegal opcode> bbcx : ()Lorg/bukkit/Server;
/*       */     //   335: aload #6
/*       */     //   337: new java/lang/StringBuilder
/*       */     //   340: dup
/*       */     //   341: invokespecial <init> : ()V
/*       */     //   344: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   347: sipush #17817
/*       */     //   350: sipush #17882
/*       */     //   353: istore #13
/*       */     //   355: istore #12
/*       */     //   357: iload #12
/*       */     //   359: iload #13
/*       */     //   361: iconst_m1
/*       */     //   362: ixor
/*       */     //   363: iand
/*       */     //   364: iload #12
/*       */     //   366: iconst_m1
/*       */     //   367: ixor
/*       */     //   368: iload #13
/*       */     //   370: iand
/*       */     //   371: ior
/*       */     //   372: aaload
/*       */     //   373: <illegal opcode> bbcy : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   378: aload_0
/*       */     //   379: <illegal opcode> bbcz : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   384: <illegal opcode> bbda : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   389: <illegal opcode> bbdb : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   394: <illegal opcode> bbdc : (Ljava/lang/Object;Lorg/bukkit/command/CommandSender;Ljava/lang/String;)Z
/*       */     //   399: pop
/*       */     //   400: goto -> 405
/*       */     //   403: astore #7
/*       */     //   405: aload_2
/*       */     //   406: ifnull -> 501
/*       */     //   409: aload_2
/*       */     //   410: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   413: sipush #11255
/*       */     //   416: istore #11
/*       */     //   418: iload #11
/*       */     //   420: ldc2_w -8459622574114985037
/*       */     //   423: l2i
/*       */     //   424: istore #13
/*       */     //   426: istore #12
/*       */     //   428: iload #12
/*       */     //   430: iload #13
/*       */     //   432: iconst_m1
/*       */     //   433: ixor
/*       */     //   434: iand
/*       */     //   435: iload #12
/*       */     //   437: iconst_m1
/*       */     //   438: ixor
/*       */     //   439: iload #13
/*       */     //   441: iand
/*       */     //   442: ior
/*       */     //   443: aaload
/*       */     //   444: <illegal opcode> bbdd : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   449: ifeq -> 455
/*       */     //   452: goto -> 501
/*       */     //   455: aload_2
/*       */     //   456: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   459: sipush #27728
/*       */     //   462: istore #11
/*       */     //   464: iload #11
/*       */     //   466: ldc2_w 270938283767262229
/*       */     //   469: l2i
/*       */     //   470: istore #13
/*       */     //   472: istore #12
/*       */     //   474: iload #12
/*       */     //   476: iload #13
/*       */     //   478: iconst_m1
/*       */     //   479: ixor
/*       */     //   480: iand
/*       */     //   481: iload #12
/*       */     //   483: iconst_m1
/*       */     //   484: ixor
/*       */     //   485: iload #13
/*       */     //   487: iand
/*       */     //   488: ior
/*       */     //   489: aaload
/*       */     //   490: <illegal opcode> bbde : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   495: ifne -> 501
/*       */     //   498: goto -> 501
/*       */     //   501: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #16909	-> 0
/*       */     //   #62332	-> 7
/*       */     //   #2402	-> 13
/*       */     //   #59251	-> 47
/*       */     //   #28938	-> 81
/*       */     //   #48145	-> 120
/*       */     //   #2622	-> 123
/*       */     //   #30887	-> 125
/*       */     //   #47744	-> 183
/*       */     //   #5284	-> 217
/*       */     //   #37557	-> 323
/*       */     //   #24696	-> 330
/*       */     //   #6582	-> 400
/*       */     //   #26605	-> 403
/*       */     //   #32314	-> 405
/*       */     //   #57264	-> 501
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   330	75	6	brsr	Lorg/bukkit/command/CommandSender;
/*       */     //   0	502	0	brss	Lme/levansj01/dc;
/*       */     //   0	502	1	brst	Lme/levansj01/ia;
/*       */     //   7	495	2	brsu	Ljava/lang/String;
/*       */     //   13	489	3	brsv	Ljava/util/Calendar;
/*       */     //   47	455	4	brsw	I
/*       */     //   81	421	5	brsx	Ljava/lang/String;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	502	1	brst	Lme/levansj01/ia<*>;
/*       */     // Exception table:
/*       */     //   from	to	target	type
/*       */     //   81	120	123	java/lang/Exception
/*       */     //   330	400	403	org/bukkit/command/CommandException
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getMaxPingTicks2() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield maxPingTick2 : Lme/levansj01/lh;
/*       */     //   4: <illegal opcode> bbty : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   9: checkcast java/lang/Integer
/*       */     //   12: <illegal opcode> bbtz : (Ljava/lang/Object;)I
/*       */     //   17: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #17239	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	18	0	brzu	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setFocus(j bsjd) {
/* 17595 */     this.focus = bsjd;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isReady() {
/* 17606 */     return this.ready;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public Queue<BiConsumer<Integer, Double>> getPingQueue() {
/* 18000 */     return this.pingQueue;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getMoveTicks() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield transactionPing : I
/*       */     //   4: aload_0
/*       */     //   5: getfield averageTransactionPing : I
/*       */     //   8: <illegal opcode> bbtk : (II)I
/*       */     //   13: i2d
/*       */     //   14: ldc2_w 125.0
/*       */     //   17: ddiv
/*       */     //   18: <illegal opcode> bbtl : (D)D
/*       */     //   23: d2i
/*       */     //   24: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #18320	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	25	0	brzp	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getAveragePing() {
/* 18557 */     return this.averagePing;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void closeInventory() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   4: <illegal opcode> bbtj : (Ljava/lang/Object;)V
/*       */     //   9: aload_0
/*       */     //   10: sipush #22001
/*       */     //   13: ldc2_w 7835402348120790513
/*       */     //   16: l2i
/*       */     //   17: istore_2
/*       */     //   18: istore_1
/*       */     //   19: iload_1
/*       */     //   20: iload_2
/*       */     //   21: ior
/*       */     //   22: iload_1
/*       */     //   23: iload_2
/*       */     //   24: iand
/*       */     //   25: iconst_m1
/*       */     //   26: ixor
/*       */     //   27: iand
/*       */     //   28: putfield inventoryOpen : Z
/*       */     //   31: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #19517	-> 0
/*       */     //   #53982	-> 9
/*       */     //   #2180	-> 31
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	32	0	brzo	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isStoppedDigging() {
/* 19526 */     return this.stoppedDigging;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(dq<?> brvm) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield effectHandler : Lme/levansj01/mn;
/*       */     //   4: ifnull -> 17
/*       */     //   7: aload_0
/*       */     //   8: getfield effectHandler : Lme/levansj01/mn;
/*       */     //   11: aload_1
/*       */     //   12: <illegal opcode> bbmp : (Ljava/lang/Object;Lme/levansj01/hr;)V
/*       */     //   17: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #20298	-> 0
/*       */     //   #56263	-> 7
/*       */     //   #33958	-> 17
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	18	1	brvm	Lme/levansj01/dq;
/*       */     //   0	18	0	brvl	Lme/levansj01/dc;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	18	1	brvm	Lme/levansj01/dq<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public df getTransaction() {
/* 20570 */     return this.transaction;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getTotalTicks() {
/* 21043 */     return this.totalTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public nq getCurrentLocation2() {
/* 21079 */     return this.currentLocation2;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public hv getAbilityHandler() {
/* 21214 */     return this.abilityHandler;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public Queue<kx> getVelocityQueue() {
/* 21510 */     return this.velocityQueue;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public mu<Integer> getConnectionFrequency() {
/* 21719 */     return this.connectionFrequency;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Warning
/*       */   @Deprecated
/*       */   public boolean isTeleporting(int bsar) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield teleportTicks : I
/*       */     //   4: aload_0
/*       */     //   5: <illegal opcode> bbux : (Ljava/lang/Object;)I
/*       */     //   10: iload_1
/*       */     //   11: imul
/*       */     //   12: if_icmple -> 18
/*       */     //   15: goto -> 74
/*       */     //   18: sipush #29789
/*       */     //   21: sipush #29788
/*       */     //   24: istore #5
/*       */     //   26: istore #4
/*       */     //   28: iload #4
/*       */     //   30: iload #5
/*       */     //   32: ior
/*       */     //   33: iload #4
/*       */     //   35: iload #5
/*       */     //   37: iand
/*       */     //   38: isub
/*       */     //   39: getstatic me/levansj01/dc.dqx : Z
/*       */     //   42: ifne -> 48
/*       */     //   45: goto -> 97
/*       */     //   48: ldc_w 507133219
/*       */     //   51: ldc2_w -4521918186873927759
/*       */     //   54: l2i
/*       */     //   55: istore #5
/*       */     //   57: istore #4
/*       */     //   59: iload #4
/*       */     //   61: iload #5
/*       */     //   63: ior
/*       */     //   64: iload #4
/*       */     //   66: iload #5
/*       */     //   68: iand
/*       */     //   69: isub
/*       */     //   70: istore_3
/*       */     //   71: goto -> 97
/*       */     //   74: sipush #31946
/*       */     //   77: sipush #31946
/*       */     //   80: istore #5
/*       */     //   82: istore #4
/*       */     //   84: iload #4
/*       */     //   86: iload #5
/*       */     //   88: ior
/*       */     //   89: iload #4
/*       */     //   91: iload #5
/*       */     //   93: iand
/*       */     //   94: iconst_m1
/*       */     //   95: ixor
/*       */     //   96: iand
/*       */     //   97: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #21765	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	98	0	bsaq	Lme/levansj01/dc;
/*       */     //   0	98	1	bsar	I
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getLastNonMoveTicks() {
/* 21820 */     return this.lastNonMoveTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handlePacketListeners(ll<?> brxv) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield checkData : Lme/levansj01/fc;
/*       */     //   4: aload_1
/*       */     //   5: <illegal opcode> bbpw : (Ljava/lang/Object;Lme/levansj01/ll;)[Lme/levansj01/ju;
/*       */     //   10: checkcast [Lme/levansj01/ju;
/*       */     //   13: astore_2
/*       */     //   14: aload_2
/*       */     //   15: ifnonnull -> 19
/*       */     //   18: return
/*       */     //   19: aload_2
/*       */     //   20: astore_3
/*       */     //   21: aload_3
/*       */     //   22: arraylength
/*       */     //   23: istore #4
/*       */     //   25: sipush #8763
/*       */     //   28: istore #12
/*       */     //   30: iload #12
/*       */     //   32: sipush #8763
/*       */     //   35: istore #14
/*       */     //   37: istore #13
/*       */     //   39: iload #13
/*       */     //   41: iload #14
/*       */     //   43: iconst_m1
/*       */     //   44: ixor
/*       */     //   45: iand
/*       */     //   46: iload #13
/*       */     //   48: iconst_m1
/*       */     //   49: ixor
/*       */     //   50: iload #14
/*       */     //   52: iand
/*       */     //   53: ior
/*       */     //   54: istore #5
/*       */     //   56: iload #5
/*       */     //   58: iload #4
/*       */     //   60: if_icmplt -> 66
/*       */     //   63: goto -> 325
/*       */     //   66: aload_3
/*       */     //   67: iload #5
/*       */     //   69: aaload
/*       */     //   70: astore #6
/*       */     //   72: aload_1
/*       */     //   73: aload #6
/*       */     //   75: <illegal opcode> bbpx : (Ljava/lang/Object;Lme/levansj01/ju;)V
/*       */     //   80: goto -> 319
/*       */     //   83: astore #7
/*       */     //   85: aload #6
/*       */     //   87: checkcast me/levansj01/ha
/*       */     //   90: astore #8
/*       */     //   92: <illegal opcode> bbpy : ()Ljava/util/logging/Logger;
/*       */     //   97: getstatic java/util/logging/Level.WARNING : Ljava/util/logging/Level;
/*       */     //   100: new java/lang/StringBuilder
/*       */     //   103: dup
/*       */     //   104: invokespecial <init> : ()V
/*       */     //   107: aload #8
/*       */     //   109: <illegal opcode> bbpz : (Ljava/lang/Object;)Lme/levansj01/j;
/*       */     //   114: <illegal opcode> bbqa : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   119: <illegal opcode> bbqb : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   124: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   127: sipush #13058
/*       */     //   130: istore #12
/*       */     //   132: iload #12
/*       */     //   134: sipush #13128
/*       */     //   137: istore #14
/*       */     //   139: istore #13
/*       */     //   141: iload #13
/*       */     //   143: iload #14
/*       */     //   145: ior
/*       */     //   146: iload #13
/*       */     //   148: iload #14
/*       */     //   150: iand
/*       */     //   151: iconst_m1
/*       */     //   152: ixor
/*       */     //   153: iand
/*       */     //   154: aaload
/*       */     //   155: <illegal opcode> bbqc : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   160: aload #8
/*       */     //   162: <illegal opcode> bbqd : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   167: <illegal opcode> bbqe : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   172: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   175: sipush #13018
/*       */     //   178: ldc2_w 2525243042638606993
/*       */     //   181: l2i
/*       */     //   182: istore #14
/*       */     //   184: istore #13
/*       */     //   186: iload #13
/*       */     //   188: iload #14
/*       */     //   190: iconst_m1
/*       */     //   191: ixor
/*       */     //   192: iand
/*       */     //   193: iload #13
/*       */     //   195: iconst_m1
/*       */     //   196: ixor
/*       */     //   197: iload #14
/*       */     //   199: iand
/*       */     //   200: ior
/*       */     //   201: aaload
/*       */     //   202: <illegal opcode> bbqf : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   207: aload #8
/*       */     //   209: <illegal opcode> bbqg : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   214: <illegal opcode> bbqh : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   219: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   222: sipush #21855
/*       */     //   225: istore #12
/*       */     //   227: iload #12
/*       */     //   229: sipush #21779
/*       */     //   232: istore #14
/*       */     //   234: istore #13
/*       */     //   236: iload #13
/*       */     //   238: iload #14
/*       */     //   240: ior
/*       */     //   241: iload #13
/*       */     //   243: iconst_m1
/*       */     //   244: ixor
/*       */     //   245: iload #14
/*       */     //   247: iconst_m1
/*       */     //   248: ixor
/*       */     //   249: ior
/*       */     //   250: iand
/*       */     //   251: aaload
/*       */     //   252: <illegal opcode> bbqi : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   257: aload_1
/*       */     //   258: <illegal opcode> bbqj : (Ljava/lang/Object;)Ljava/lang/Class;
/*       */     //   263: <illegal opcode> bbqk : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   268: <illegal opcode> bbql : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   273: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   276: sipush #20757
/*       */     //   279: sipush #20824
/*       */     //   282: istore #14
/*       */     //   284: istore #13
/*       */     //   286: iload #13
/*       */     //   288: iload #14
/*       */     //   290: ior
/*       */     //   291: iload #13
/*       */     //   293: iconst_m1
/*       */     //   294: ixor
/*       */     //   295: iload #14
/*       */     //   297: iconst_m1
/*       */     //   298: ixor
/*       */     //   299: ior
/*       */     //   300: iand
/*       */     //   301: aaload
/*       */     //   302: <illegal opcode> bbqm : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   307: <illegal opcode> bbqn : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   312: aload #7
/*       */     //   314: <illegal opcode> bbqo : (Ljava/lang/Object;Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
/*       */     //   319: iinc #5, 1
/*       */     //   322: goto -> 56
/*       */     //   325: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #21897	-> 0
/*       */     //   #11166	-> 14
/*       */     //   #44430	-> 18
/*       */     //   #60046	-> 19
/*       */     //   #47782	-> 72
/*       */     //   #8183	-> 80
/*       */     //   #14993	-> 83
/*       */     //   #24585	-> 85
/*       */     //   #11955	-> 92
/*       */     //   #18764	-> 319
/*       */     //   #6020	-> 325
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   92	227	8	brxr	Lme/levansj01/ha;
/*       */     //   85	234	7	brxs	Ljava/lang/Throwable;
/*       */     //   72	247	6	brxt	Lme/levansj01/ju;
/*       */     //   0	326	0	brxu	Lme/levansj01/dc;
/*       */     //   0	326	1	brxv	Lme/levansj01/ll;
/*       */     //   14	312	2	brxw	[Lme/levansj01/ju;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	326	1	brxv	Lme/levansj01/ll<*>;
/*       */     // Exception table:
/*       */     //   from	to	target	type
/*       */     //   72	80	83	java/lang/Throwable
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public mn getEffectHandler() {
/* 21962 */     return this.effectHandler;
/*       */   }
/*       */   public Player getPlayer() {
/* 21965 */     return this.player;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void reset(ij brzc) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   4: aload_1
/*       */     //   5: <illegal opcode> bbso : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   10: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #22307	-> 0
/*       */     //   #47105	-> 10
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	11	1	brzc	Lme/levansj01/ij;
/*       */     //   0	11	0	brzb	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(s<?> brwt) {
/*       */     // Byte code:
/*       */     //   0: <illegal opcode> bbob : ()J
/*       */     //   5: lstore_2
/*       */     //   6: aload_0
/*       */     //   7: getfield teleportList : Ljava/util/Queue;
/*       */     //   10: sipush #32380
/*       */     //   13: ldc2_w 5606430629211176340
/*       */     //   16: l2i
/*       */     //   17: istore #15
/*       */     //   19: istore #14
/*       */     //   21: iload #14
/*       */     //   23: iload #15
/*       */     //   25: ior
/*       */     //   26: iload #14
/*       */     //   28: iload #15
/*       */     //   30: iand
/*       */     //   31: isub
/*       */     //   32: <illegal opcode> bboc : (Ljava/util/Queue;I)Ljava/util/Queue;
/*       */     //   37: pop
/*       */     //   38: aload_0
/*       */     //   39: getfield teleports : Ljava/util/Queue;
/*       */     //   42: sipush #11250
/*       */     //   45: sipush #10266
/*       */     //   48: istore #15
/*       */     //   50: istore #14
/*       */     //   52: iload #14
/*       */     //   54: iload #15
/*       */     //   56: ior
/*       */     //   57: iload #14
/*       */     //   59: iload #15
/*       */     //   61: iand
/*       */     //   62: iconst_m1
/*       */     //   63: ixor
/*       */     //   64: iand
/*       */     //   65: <illegal opcode> bbod : (Ljava/util/Queue;I)Ljava/util/Queue;
/*       */     //   70: pop
/*       */     //   71: aload_0
/*       */     //   72: getfield teleportHandler : Lme/levansj01/jn;
/*       */     //   75: ifnull -> 88
/*       */     //   78: aload_0
/*       */     //   79: getfield teleportHandler : Lme/levansj01/jn;
/*       */     //   82: aload_1
/*       */     //   83: <illegal opcode> bboe : (Ljava/lang/Object;Lme/levansj01/s;)V
/*       */     //   88: aload_1
/*       */     //   89: aload_0
/*       */     //   90: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   93: getstatic me/levansj01/ij.TOTAL : Lme/levansj01/ij;
/*       */     //   96: <illegal opcode> bbof : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   101: lload_2
/*       */     //   102: <illegal opcode> bbog : (Ljava/lang/Object;IJ)Lme/levansj01/bi;
/*       */     //   107: astore #4
/*       */     //   109: aload_0
/*       */     //   110: getfield teleports : Ljava/util/Queue;
/*       */     //   113: aload #4
/*       */     //   115: <illegal opcode> bboh : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   120: pop
/*       */     //   121: aload_0
/*       */     //   122: getfield recentTeleports : Lme/levansj01/mu;
/*       */     //   125: aload #4
/*       */     //   127: <illegal opcode> bboi : (Ljava/lang/Object;Ljava/lang/Object;)V
/*       */     //   132: aload_1
/*       */     //   133: aload_0
/*       */     //   134: <illegal opcode> bboj : (Ljava/lang/Object;Lme/levansj01/dc;)Lme/levansj01/gz;
/*       */     //   139: astore #5
/*       */     //   141: aload_0
/*       */     //   142: getfield teleportList : Ljava/util/Queue;
/*       */     //   145: aload #5
/*       */     //   147: <illegal opcode> bbok : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*       */     //   152: pop
/*       */     //   153: aload_0
/*       */     //   154: sipush #14229
/*       */     //   157: istore #13
/*       */     //   159: iload #13
/*       */     //   161: ldc2_w 7792070401596602261
/*       */     //   164: l2i
/*       */     //   165: istore #15
/*       */     //   167: istore #14
/*       */     //   169: iload #14
/*       */     //   171: iload #15
/*       */     //   173: ior
/*       */     //   174: iload #14
/*       */     //   176: iload #15
/*       */     //   178: iand
/*       */     //   179: isub
/*       */     //   180: putfield teleportTicks : I
/*       */     //   183: aload_0
/*       */     //   184: lload_2
/*       */     //   185: putfield lastTeleport : J
/*       */     //   188: aload_0
/*       */     //   189: dconst_0
/*       */     //   190: putfield velY : D
/*       */     //   193: aload_0
/*       */     //   194: dconst_0
/*       */     //   195: putfield lastVelZ : D
/*       */     //   198: aload_0
/*       */     //   199: dconst_0
/*       */     //   200: putfield lastVelY : D
/*       */     //   203: aload_0
/*       */     //   204: dconst_0
/*       */     //   205: putfield lastVelX : D
/*       */     //   208: aload_0
/*       */     //   209: getfield checkData : Lme/levansj01/fc;
/*       */     //   212: <illegal opcode> bbol : (Ljava/lang/Object;)[Lme/levansj01/jy;
/*       */     //   217: checkcast [Lme/levansj01/jy;
/*       */     //   220: astore #6
/*       */     //   222: aload #6
/*       */     //   224: arraylength
/*       */     //   225: istore #7
/*       */     //   227: ldc2_w 3786275995141549530
/*       */     //   230: l2i
/*       */     //   231: sipush #11738
/*       */     //   234: istore #15
/*       */     //   236: istore #14
/*       */     //   238: iload #14
/*       */     //   240: iload #15
/*       */     //   242: ior
/*       */     //   243: iload #14
/*       */     //   245: iconst_m1
/*       */     //   246: ixor
/*       */     //   247: iload #15
/*       */     //   249: iconst_m1
/*       */     //   250: ixor
/*       */     //   251: ior
/*       */     //   252: iand
/*       */     //   253: istore #8
/*       */     //   255: iload #8
/*       */     //   257: iload #7
/*       */     //   259: if_icmplt -> 265
/*       */     //   262: goto -> 287
/*       */     //   265: aload #6
/*       */     //   267: iload #8
/*       */     //   269: aaload
/*       */     //   270: astore #9
/*       */     //   272: aload #9
/*       */     //   274: aload_1
/*       */     //   275: lload_2
/*       */     //   276: <illegal opcode> bbom : (Ljava/lang/Object;Lme/levansj01/s;J)V
/*       */     //   281: iinc #8, 1
/*       */     //   284: goto -> 255
/*       */     //   287: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #22914	-> 0
/*       */     //   #21241	-> 6
/*       */     //   #29571	-> 38
/*       */     //   #13798	-> 71
/*       */     //   #15722	-> 78
/*       */     //   #26794	-> 88
/*       */     //   #23008	-> 109
/*       */     //   #34784	-> 121
/*       */     //   #39468	-> 132
/*       */     //   #2926	-> 141
/*       */     //   #45342	-> 153
/*       */     //   #65354	-> 183
/*       */     //   #60637	-> 188
/*       */     //   #60156	-> 193
/*       */     //   #40184	-> 198
/*       */     //   #55301	-> 203
/*       */     //   #47688	-> 208
/*       */     //   #45083	-> 272
/*       */     //   #19993	-> 281
/*       */     //   #362	-> 287
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   141	147	5	brww	Lme/levansj01/gz;
/*       */     //   6	282	2	brwu	J
/*       */     //   272	9	9	brwr	Lme/levansj01/jy;
/*       */     //   0	288	0	brws	Lme/levansj01/dc;
/*       */     //   0	288	1	brwt	Lme/levansj01/s;
/*       */     //   109	179	4	brwv	Lme/levansj01/bi;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	288	1	brwt	Lme/levansj01/s<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getSuffocationTicks() {
/* 23810 */     return this.suffocationTicks;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public boolean canFly() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield allowFlightTicks : I
/*       */     //   4: aload_0
/*       */     //   5: <illegal opcode> bbvw : (Ljava/lang/Object;)I
/*       */     //   10: sipush #29502
/*       */     //   13: istore #4
/*       */     //   15: iload #4
/*       */     //   17: ldc2_w -5259876486965333188
/*       */     //   20: l2i
/*       */     //   21: istore #6
/*       */     //   23: istore #5
/*       */     //   25: iload #5
/*       */     //   27: iload #6
/*       */     //   29: ior
/*       */     //   30: iload #5
/*       */     //   32: iload #6
/*       */     //   34: iand
/*       */     //   35: iconst_m1
/*       */     //   36: ixor
/*       */     //   37: iand
/*       */     //   38: istore #6
/*       */     //   40: istore #5
/*       */     //   42: iload #5
/*       */     //   44: iload #6
/*       */     //   46: iconst_m1
/*       */     //   47: ixor
/*       */     //   48: isub
/*       */     //   49: iconst_1
/*       */     //   50: isub
/*       */     //   51: sipush #10872
/*       */     //   54: istore #4
/*       */     //   56: ldc2_w 1904707507742321275
/*       */     //   59: l2i
/*       */     //   60: iload #4
/*       */     //   62: istore #6
/*       */     //   64: istore #5
/*       */     //   66: iload #5
/*       */     //   68: iload #6
/*       */     //   70: ior
/*       */     //   71: iload #5
/*       */     //   73: iconst_m1
/*       */     //   74: ixor
/*       */     //   75: iload #6
/*       */     //   77: iconst_m1
/*       */     //   78: ixor
/*       */     //   79: ior
/*       */     //   80: iand
/*       */     //   81: imul
/*       */     //   82: if_icmple -> 88
/*       */     //   85: goto -> 121
/*       */     //   88: sipush #5862
/*       */     //   91: istore #4
/*       */     //   93: iload #4
/*       */     //   95: ldc2_w 3177280148311381735
/*       */     //   98: l2i
/*       */     //   99: istore #6
/*       */     //   101: istore #5
/*       */     //   103: iload #5
/*       */     //   105: iload #6
/*       */     //   107: ior
/*       */     //   108: iload #5
/*       */     //   110: iconst_m1
/*       */     //   111: ixor
/*       */     //   112: iload #6
/*       */     //   114: iconst_m1
/*       */     //   115: ixor
/*       */     //   116: ior
/*       */     //   117: iand
/*       */     //   118: goto -> 147
/*       */     //   121: sipush #19910
/*       */     //   124: istore #4
/*       */     //   126: ldc2_w -600192246205100602
/*       */     //   129: l2i
/*       */     //   130: iload #4
/*       */     //   132: istore #6
/*       */     //   134: istore #5
/*       */     //   136: iload #5
/*       */     //   138: iload #6
/*       */     //   140: ior
/*       */     //   141: iload #5
/*       */     //   143: iload #6
/*       */     //   145: iand
/*       */     //   146: isub
/*       */     //   147: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #23889	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	148	0	bsbl	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public long getLastRespawn() {
/* 23928 */     return this.lastRespawn;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(of<?> brsq) {
/* 24142 */     char c = '䍰'; int k = c, i = (int)3131491557803311984L; this.inventoryOpen = i & (k ^ 0xFFFFFFFF) | (i ^ 0xFFFFFFFF) & k;
/*       */     c = '㓳';
/*       */     k = (int)5951548959594919155L;
/*       */     i = c;
/*       */     this.blocking = (i | k) & (i & k ^ 0xFFFFFFFF);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public jz getTickerMap() {
/* 24440 */     return this.tickerMap;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(hy<?> brsd) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: sipush #5221
/*       */     //   4: istore #5
/*       */     //   6: ldc2_w -6304898487427263387
/*       */     //   9: l2i
/*       */     //   10: iload #5
/*       */     //   12: istore #7
/*       */     //   14: istore #6
/*       */     //   16: iload #6
/*       */     //   18: iload #7
/*       */     //   20: ior
/*       */     //   21: iload #6
/*       */     //   23: iload #7
/*       */     //   25: iand
/*       */     //   26: iconst_m1
/*       */     //   27: ixor
/*       */     //   28: iand
/*       */     //   29: putfield blocking : Z
/*       */     //   32: aload_0
/*       */     //   33: getfield swingDigging : Z
/*       */     //   36: ifeq -> 42
/*       */     //   39: goto -> 65
/*       */     //   42: aload_0
/*       */     //   43: getfield placing : Z
/*       */     //   46: ifeq -> 52
/*       */     //   49: goto -> 65
/*       */     //   52: aload_0
/*       */     //   53: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   56: getstatic me/levansj01/ij.POSSIBLE_SWINGS : Lme/levansj01/ij;
/*       */     //   59: <illegal opcode> bbax : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   64: pop
/*       */     //   65: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #24602	-> 0
/*       */     //   #43123	-> 32
/*       */     //   #14028	-> 52
/*       */     //   #45340	-> 65
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	66	1	brsd	Lme/levansj01/hy;
/*       */     //   0	66	0	brsc	Lme/levansj01/dc;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	66	1	brsd	Lme/levansj01/hy<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public lh<Integer> getSpeedLevel() {
/* 25045 */     return this.speedLevel;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public is getDiggingBlock() {
/* 25747 */     return this.diggingBlock;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(oh<?> bruh) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield vehicle : Z
/*       */     //   4: ifeq -> 10
/*       */     //   7: goto -> 22
/*       */     //   10: aload_0
/*       */     //   11: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   14: <illegal opcode> bbkd : (Ljava/lang/Object;)Lorg/bukkit/entity/Entity;
/*       */     //   19: ifnull -> 98
/*       */     //   22: aload_1
/*       */     //   23: <illegal opcode> bbke : (Ljava/lang/Object;)D
/*       */     //   28: <illegal opcode> bbkf : (D)D
/*       */     //   33: ldc2_w 0.9800000190734863
/*       */     //   36: dcmpg
/*       */     //   37: ifle -> 43
/*       */     //   40: goto -> 98
/*       */     //   43: aload_1
/*       */     //   44: <illegal opcode> bbkg : (Ljava/lang/Object;)D
/*       */     //   49: <illegal opcode> bbkh : (D)D
/*       */     //   54: ldc2_w 0.9800000190734863
/*       */     //   57: dcmpg
/*       */     //   58: ifle -> 64
/*       */     //   61: goto -> 98
/*       */     //   64: aload_0
/*       */     //   65: sipush #17207
/*       */     //   68: ldc2_w -1866846679538318538
/*       */     //   71: l2i
/*       */     //   72: istore_3
/*       */     //   73: istore_2
/*       */     //   74: iload_2
/*       */     //   75: iload_3
/*       */     //   76: ior
/*       */     //   77: iload_2
/*       */     //   78: iload_3
/*       */     //   79: iand
/*       */     //   80: iconst_m1
/*       */     //   81: ixor
/*       */     //   82: iand
/*       */     //   83: putfield wasVehicle : Z
/*       */     //   86: aload_0
/*       */     //   87: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   90: getstatic me/levansj01/ij.VEHICLE : Lme/levansj01/ij;
/*       */     //   93: <illegal opcode> bbki : (Ljava/lang/Object;Lme/levansj01/ij;)V
/*       */     //   98: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #25882	-> 0
/*       */     //   #24939	-> 64
/*       */     //   #51099	-> 86
/*       */     //   #8843	-> 98
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	99	0	brug	Lme/levansj01/dc;
/*       */     //   0	99	1	bruh	Lme/levansj01/oh;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	99	1	bruh	Lme/levansj01/oh<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isBlocking() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield blockTicks : I
/*       */     //   4: aload_0
/*       */     //   5: <illegal opcode> bbuy : (Ljava/lang/Object;)I
/*       */     //   10: sipush #10849
/*       */     //   13: istore #5
/*       */     //   15: ldc2_w 103985547692747363
/*       */     //   18: l2i
/*       */     //   19: iload #5
/*       */     //   21: istore #7
/*       */     //   23: istore #6
/*       */     //   25: iload #6
/*       */     //   27: iload #7
/*       */     //   29: ior
/*       */     //   30: iload #6
/*       */     //   32: iload #7
/*       */     //   34: iand
/*       */     //   35: isub
/*       */     //   36: istore #7
/*       */     //   38: istore #6
/*       */     //   40: iload #6
/*       */     //   42: iload #7
/*       */     //   44: iconst_m1
/*       */     //   45: ixor
/*       */     //   46: isub
/*       */     //   47: iconst_1
/*       */     //   48: isub
/*       */     //   49: sipush #5585
/*       */     //   52: ldc2_w -4887051653267581485
/*       */     //   55: l2i
/*       */     //   56: istore #7
/*       */     //   58: istore #6
/*       */     //   60: iload #6
/*       */     //   62: iload #7
/*       */     //   64: ior
/*       */     //   65: iload #6
/*       */     //   67: iconst_m1
/*       */     //   68: ixor
/*       */     //   69: iload #7
/*       */     //   71: iconst_m1
/*       */     //   72: ixor
/*       */     //   73: ior
/*       */     //   74: iand
/*       */     //   75: imul
/*       */     //   76: if_icmpge -> 82
/*       */     //   79: goto -> 146
/*       */     //   82: ldc2_w -1748147325508763640
/*       */     //   85: l2i
/*       */     //   86: sipush #16393
/*       */     //   89: istore #7
/*       */     //   91: istore #6
/*       */     //   93: iload #6
/*       */     //   95: iload #7
/*       */     //   97: ior
/*       */     //   98: iload #6
/*       */     //   100: iconst_m1
/*       */     //   101: ixor
/*       */     //   102: iload #7
/*       */     //   104: iconst_m1
/*       */     //   105: ixor
/*       */     //   106: ior
/*       */     //   107: iand
/*       */     //   108: getstatic me/levansj01/dc.dqx : Z
/*       */     //   111: ifne -> 117
/*       */     //   114: goto -> 173
/*       */     //   117: ldc_w 1134220666
/*       */     //   120: sipush #6476
/*       */     //   123: istore #7
/*       */     //   125: istore #6
/*       */     //   127: iload #6
/*       */     //   129: iload #7
/*       */     //   131: iconst_m1
/*       */     //   132: ixor
/*       */     //   133: iand
/*       */     //   134: iload #6
/*       */     //   136: iconst_m1
/*       */     //   137: ixor
/*       */     //   138: iload #7
/*       */     //   140: iand
/*       */     //   141: ior
/*       */     //   142: istore_2
/*       */     //   143: goto -> 173
/*       */     //   146: sipush #22877
/*       */     //   149: istore #5
/*       */     //   151: iload #5
/*       */     //   153: sipush #22877
/*       */     //   156: istore #7
/*       */     //   158: istore #6
/*       */     //   160: iload #6
/*       */     //   162: iload #7
/*       */     //   164: ior
/*       */     //   165: iload #6
/*       */     //   167: iload #7
/*       */     //   169: iand
/*       */     //   170: iconst_m1
/*       */     //   171: ixor
/*       */     //   172: iand
/*       */     //   173: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #25895	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	174	0	bsas	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public String getBrand() {
/* 25979 */     return this.brand;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void resetPingTicks() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield shouldHaveReceivedPing : Lme/levansj01/lh;
/*       */     //   4: <illegal opcode> bbtq : (Ljava/lang/Object;)V
/*       */     //   9: aload_0
/*       */     //   10: getfield pingTicks : Lme/levansj01/lh;
/*       */     //   13: <illegal opcode> bbtr : (Ljava/lang/Object;)V
/*       */     //   18: aload_0
/*       */     //   19: getfield maxPingTicks : Lme/levansj01/lh;
/*       */     //   22: <illegal opcode> bbts : (Ljava/lang/Object;)V
/*       */     //   27: aload_0
/*       */     //   28: getfield maxPingTick2 : Lme/levansj01/lh;
/*       */     //   31: <illegal opcode> bbtt : (Ljava/lang/Object;)V
/*       */     //   36: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #26427	-> 0
/*       */     //   #23843	-> 9
/*       */     //   #20363	-> 18
/*       */     //   #9383	-> 27
/*       */     //   #46869	-> 36
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	37	0	brzr	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isWasVehicle() {
/* 26486 */     return this.wasVehicle;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getAverageTransactionPing() {
/* 26981 */     return this.averageTransactionPing;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public long getLastClientTransaction() {
/* 27037 */     return this.lastClientTransaction;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public double getLastVelX() {
/* 27239 */     return this.lastVelX;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public df getNextTransaction() {
/* 27352 */     return this.nextTransaction;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void preProcess(ll<?> brxl) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: <illegal opcode> bbpa : ()J
/*       */     //   6: putfield timestamp : J
/*       */     //   9: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #27445	-> 0
/*       */     //   #29702	-> 9
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	10	0	brxk	Lme/levansj01/dc;
/*       */     //   0	10	1	brxl	Lme/levansj01/ll;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	10	1	brxl	Lme/levansj01/ll<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public short incrementTransactionId() {
/* 27762 */     short bsjh = this.lastTransactionID;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 32202 */     short s1 = 32466; int i = 32467; short s2 = s1; i = (s2 | i) & (s2 ^ 0xFFFFFFFF | i ^ 0xFFFFFFFF); s2 = bsjh; this.lastTransactionID = (short)(2 * (s2 | i) - (s2 ^ i));
/*       */     return bsjh;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setDebug(boolean bshv) {
/*       */     this.debug = bshv;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public gz getLastLastLocation() {
/*       */     return this.lastLastLocation;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isTeleportingV2() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   4: getstatic me/levansj01/ij.TELEPORT : Lme/levansj01/ij;
/*       */     //   7: <illegal opcode> bbsj : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   12: ifeq -> 18
/*       */     //   15: goto -> 49
/*       */     //   18: sipush #26891
/*       */     //   21: istore #4
/*       */     //   23: iload #4
/*       */     //   25: ldc2_w 6345741023662074122
/*       */     //   28: l2i
/*       */     //   29: istore #6
/*       */     //   31: istore #5
/*       */     //   33: iload #5
/*       */     //   35: iload #6
/*       */     //   37: ior
/*       */     //   38: iload #5
/*       */     //   40: iload #6
/*       */     //   42: iand
/*       */     //   43: iconst_m1
/*       */     //   44: ixor
/*       */     //   45: iand
/*       */     //   46: goto -> 72
/*       */     //   49: sipush #12859
/*       */     //   52: sipush #12859
/*       */     //   55: istore #6
/*       */     //   57: istore #5
/*       */     //   59: iload #5
/*       */     //   61: iload #6
/*       */     //   63: ior
/*       */     //   64: iload #5
/*       */     //   66: iload #6
/*       */     //   68: iand
/*       */     //   69: iconst_m1
/*       */     //   70: ixor
/*       */     //   71: iand
/*       */     //   72: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #28506	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	73	0	bryy	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setTransactionPing(int bsih) {
/*       */     this.transactionPing = bsih;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getPing() {
/*       */     return this.ping;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public ha getSpoofBanCheck() {
/*       */     return this.spoofBanCheck;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(cw<?> brsk) {
/*       */     // Byte code:
/*       */     //   0: aload_1
/*       */     //   1: <illegal opcode> bbbp : (Ljava/lang/Object;)Lorg/bukkit/inventory/ItemStack;
/*       */     //   6: astore_2
/*       */     //   7: aload_1
/*       */     //   8: <illegal opcode> bbbq : (Ljava/lang/Object;)Z
/*       */     //   13: ifeq -> 19
/*       */     //   16: goto -> 108
/*       */     //   19: aload_0
/*       */     //   20: aload_1
/*       */     //   21: <illegal opcode> bbbr : (Ljava/lang/Object;)Lme/levansj01/is;
/*       */     //   26: putfield lastBlockPosition : Lme/levansj01/is;
/*       */     //   29: aload_0
/*       */     //   30: aload_1
/*       */     //   31: <illegal opcode> bbbs : (Ljava/lang/Object;)I
/*       */     //   36: putfield lastFace : I
/*       */     //   39: aload_2
/*       */     //   40: ifnull -> 60
/*       */     //   43: aload_0
/*       */     //   44: aload_2
/*       */     //   45: <illegal opcode> bbbt : (Ljava/lang/Object;Lorg/bukkit/inventory/ItemStack;)V
/*       */     //   50: aload_0
/*       */     //   51: aload_2
/*       */     //   52: <illegal opcode> bbbu : (Lorg/bukkit/inventory/ItemStack;)Z
/*       */     //   57: putfield blocking : Z
/*       */     //   60: aload_0
/*       */     //   61: ldc2_w 6173853821136031418
/*       */     //   64: l2i
/*       */     //   65: sipush #20155
/*       */     //   68: istore #5
/*       */     //   70: istore #4
/*       */     //   72: iload #4
/*       */     //   74: iload #5
/*       */     //   76: iconst_m1
/*       */     //   77: ixor
/*       */     //   78: iand
/*       */     //   79: iload #4
/*       */     //   81: iconst_m1
/*       */     //   82: ixor
/*       */     //   83: iload #5
/*       */     //   85: iand
/*       */     //   86: ior
/*       */     //   87: putfield placing : Z
/*       */     //   90: aload_0
/*       */     //   91: getfield world : Lme/levansj01/f;
/*       */     //   94: ifnull -> 107
/*       */     //   97: aload_0
/*       */     //   98: getfield world : Lme/levansj01/f;
/*       */     //   101: aload_1
/*       */     //   102: <illegal opcode> bbbv : (Ljava/lang/Object;Lme/levansj01/cw;)V
/*       */     //   107: return
/*       */     //   108: <illegal opcode> bbbw : ()[Lme/levansj01/gj;
/*       */     //   113: checkcast [Lme/levansj01/gj;
/*       */     //   116: aload_1
/*       */     //   117: <illegal opcode> bbbx : (Ljava/lang/Object;)I
/*       */     //   122: aaload
/*       */     //   123: astore_3
/*       */     //   124: getstatic me/levansj01/dc.$SwitchMap$me$levansj01$verus$compat$api$wrapper$EnumHand : [I
/*       */     //   127: aload_3
/*       */     //   128: <illegal opcode> bbby : (Ljava/lang/Object;)I
/*       */     //   133: iaload
/*       */     //   134: lookupswitch default -> 188, 1 -> 160, 2 -> 173
/*       */     //   160: aload_0
/*       */     //   161: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   164: <illegal opcode> bbbz : (Ljava/lang/Object;)Lorg/bukkit/inventory/ItemStack;
/*       */     //   169: astore_2
/*       */     //   170: goto -> 188
/*       */     //   173: <illegal opcode> bbca : ()Lme/levansj01/verus/compat/NMSManager;
/*       */     //   178: aload_0
/*       */     //   179: getfield player : Lorg/bukkit/entity/Player;
/*       */     //   182: <illegal opcode> bbcb : (Ljava/lang/Object;Lorg/bukkit/entity/Player;)Lorg/bukkit/inventory/ItemStack;
/*       */     //   187: astore_2
/*       */     //   188: aload_2
/*       */     //   189: ifnull -> 214
/*       */     //   192: aload_0
/*       */     //   193: aload_2
/*       */     //   194: <illegal opcode> bbcc : (Ljava/lang/Object;)Lorg/bukkit/Material;
/*       */     //   199: <illegal opcode> bbcd : (Ljava/lang/Object;Lorg/bukkit/Material;)V
/*       */     //   204: aload_0
/*       */     //   205: aload_2
/*       */     //   206: <illegal opcode> bbce : (Lorg/bukkit/inventory/ItemStack;)Z
/*       */     //   211: putfield blocking : Z
/*       */     //   214: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #30406	-> 0
/*       */     //   #7366	-> 7
/*       */     //   #43846	-> 19
/*       */     //   #18374	-> 29
/*       */     //   #22637	-> 39
/*       */     //   #5116	-> 43
/*       */     //   #46899	-> 50
/*       */     //   #2191	-> 60
/*       */     //   #52807	-> 90
/*       */     //   #26591	-> 97
/*       */     //   #5722	-> 107
/*       */     //   #215	-> 108
/*       */     //   #3507	-> 124
/*       */     //   #42791	-> 160
/*       */     //   #57166	-> 170
/*       */     //   #29700	-> 173
/*       */     //   #24250	-> 188
/*       */     //   #31645	-> 192
/*       */     //   #15903	-> 204
/*       */     //   #42230	-> 214
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	215	0	brsj	Lme/levansj01/dc;
/*       */     //   7	208	2	brsl	Lorg/bukkit/inventory/ItemStack;
/*       */     //   124	91	3	brsm	Lme/levansj01/gj;
/*       */     //   0	215	1	brsk	Lme/levansj01/cw;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	215	1	brsk	Lme/levansj01/cw<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handleIn(ed brrz) {
/*       */     // Byte code:
/*       */     //   0: aload_1
/*       */     //   1: <illegal opcode> bazi : (Ljava/lang/Object;)S
/*       */     //   6: istore_2
/*       */     //   7: aload_0
/*       */     //   8: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   11: getstatic me/levansj01/ij.LAST_RECEIVED_TRANSACTION : Lme/levansj01/ij;
/*       */     //   14: getstatic me/levansj01/ij.TOTAL : Lme/levansj01/ij;
/*       */     //   17: <illegal opcode> bazj : (Ljava/lang/Object;Lme/levansj01/ij;Lme/levansj01/ij;)V
/*       */     //   22: aload_0
/*       */     //   23: getfield transactionMap : Ljava/util/Map;
/*       */     //   26: iload_2
/*       */     //   27: <illegal opcode> bazk : (S)Ljava/lang/Short;
/*       */     //   32: <illegal opcode> bazl : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   37: checkcast me/levansj01/df
/*       */     //   40: astore_3
/*       */     //   41: aload_3
/*       */     //   42: ifnull -> 835
/*       */     //   45: aload_3
/*       */     //   46: <illegal opcode> bazm : (Ljava/lang/Object;)Z
/*       */     //   51: ifne -> 57
/*       */     //   54: goto -> 835
/*       */     //   57: aload_0
/*       */     //   58: aload_3
/*       */     //   59: putfield transaction : Lme/levansj01/df;
/*       */     //   62: aload_0
/*       */     //   63: sipush #32484
/*       */     //   66: istore #16
/*       */     //   68: ldc2_w 2569432518038159077
/*       */     //   71: l2i
/*       */     //   72: iload #16
/*       */     //   74: istore #18
/*       */     //   76: istore #17
/*       */     //   78: iload #17
/*       */     //   80: iload #18
/*       */     //   82: iconst_m1
/*       */     //   83: ixor
/*       */     //   84: iand
/*       */     //   85: iload #17
/*       */     //   87: iconst_m1
/*       */     //   88: ixor
/*       */     //   89: iload #18
/*       */     //   91: iand
/*       */     //   92: ior
/*       */     //   93: putfield receivedTransaction : Z
/*       */     //   96: aload_3
/*       */     //   97: aload_0
/*       */     //   98: getfield timestamp : J
/*       */     //   101: aload_0
/*       */     //   102: <illegal opcode> bazn : (Ljava/lang/Object;JLme/levansj01/dc;)V
/*       */     //   107: aload_0
/*       */     //   108: dup
/*       */     //   109: getfield receivedTransactions : I
/*       */     //   112: sipush #30974
/*       */     //   115: ldc2_w 5565358230621681919
/*       */     //   118: l2i
/*       */     //   119: istore #18
/*       */     //   121: istore #17
/*       */     //   123: iload #17
/*       */     //   125: iload #18
/*       */     //   127: ior
/*       */     //   128: iload #17
/*       */     //   130: iload #18
/*       */     //   132: iand
/*       */     //   133: isub
/*       */     //   134: istore #18
/*       */     //   136: istore #17
/*       */     //   138: iconst_2
/*       */     //   139: iload #17
/*       */     //   141: iload #18
/*       */     //   143: ior
/*       */     //   144: imul
/*       */     //   145: iload #17
/*       */     //   147: iload #18
/*       */     //   149: ixor
/*       */     //   150: isub
/*       */     //   151: putfield receivedTransactions : I
/*       */     //   154: aload_0
/*       */     //   155: getfield nextTrans : Ljava/util/Queue;
/*       */     //   158: aload_3
/*       */     //   159: <illegal opcode> get : (Lme/levansj01/df;)Ljava/util/function/Supplier;
/*       */     //   164: <illegal opcode> bazo : (Ljava/util/Queue;Ljava/util/function/Supplier;)V
/*       */     //   169: aload_0
/*       */     //   170: <illegal opcode> get : (Lme/levansj01/dc;)Ljava/util/function/Supplier;
/*       */     //   175: <illegal opcode> bazp : (Ljava/util/function/Supplier;)Lme/levansj01/iu;
/*       */     //   180: astore #5
/*       */     //   182: aload_0
/*       */     //   183: aload_0
/*       */     //   184: getfield transactionPing : I
/*       */     //   187: putfield lastTransactionPing : I
/*       */     //   190: aload_0
/*       */     //   191: aload_3
/*       */     //   192: <illegal opcode> bazq : (Ljava/lang/Object;)Ljava/lang/Long;
/*       */     //   197: <illegal opcode> bazr : (Ljava/lang/Object;)I
/*       */     //   202: putfield transactionPing : I
/*       */     //   205: <illegal opcode> bazs : ()Lme/levansj01/on;
/*       */     //   210: <illegal opcode> bazt : (Ljava/lang/Object;)Lme/levansj01/kh;
/*       */     //   215: astore #6
/*       */     //   217: aload #6
/*       */     //   219: <illegal opcode> bazu : (Ljava/lang/Object;)Z
/*       */     //   224: ifne -> 230
/*       */     //   227: goto -> 387
/*       */     //   230: aload_0
/*       */     //   231: getfield transactionPing : I
/*       */     //   234: i2l
/*       */     //   235: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
/*       */     //   238: aload #6
/*       */     //   240: <illegal opcode> bazv : (Ljava/lang/Object;)I
/*       */     //   245: i2l
/*       */     //   246: <illegal opcode> bazw : (Ljava/lang/Object;J)J
/*       */     //   251: lcmp
/*       */     //   252: ifgt -> 258
/*       */     //   255: goto -> 387
/*       */     //   258: <illegal opcode> bazx : ()Lme/levansj01/launcher/VerusLauncher;
/*       */     //   263: <illegal opcode> bazy : (Ljava/lang/Object;)Ljava/util/logging/Logger;
/*       */     //   268: new java/lang/StringBuilder
/*       */     //   271: dup
/*       */     //   272: invokespecial <init> : ()V
/*       */     //   275: aload_0
/*       */     //   276: <illegal opcode> bazz : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   281: <illegal opcode> bbaa : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   286: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   289: sipush #30256
/*       */     //   292: istore #16
/*       */     //   294: iload #16
/*       */     //   296: ldc2_w -6630398450879924724
/*       */     //   299: l2i
/*       */     //   300: istore #18
/*       */     //   302: istore #17
/*       */     //   304: iload #17
/*       */     //   306: iload #18
/*       */     //   308: ior
/*       */     //   309: iload #17
/*       */     //   311: iload #18
/*       */     //   313: iand
/*       */     //   314: iconst_m1
/*       */     //   315: ixor
/*       */     //   316: iand
/*       */     //   317: aaload
/*       */     //   318: <illegal opcode> bbab : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   323: aload #6
/*       */     //   325: <illegal opcode> bbac : (Ljava/lang/Object;)I
/*       */     //   330: <illegal opcode> bbad : (Ljava/lang/Object;I)Ljava/lang/StringBuilder;
/*       */     //   335: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   338: ldc2_w -2354897299580116691
/*       */     //   341: l2i
/*       */     //   342: sipush #8464
/*       */     //   345: istore #18
/*       */     //   347: istore #17
/*       */     //   349: iload #17
/*       */     //   351: iload #18
/*       */     //   353: iconst_m1
/*       */     //   354: ixor
/*       */     //   355: iand
/*       */     //   356: iload #17
/*       */     //   358: iconst_m1
/*       */     //   359: ixor
/*       */     //   360: iload #18
/*       */     //   362: iand
/*       */     //   363: ior
/*       */     //   364: aaload
/*       */     //   365: <illegal opcode> bbae : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   370: <illegal opcode> bbaf : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   375: <illegal opcode> bbag : (Ljava/lang/Object;Ljava/lang/String;)V
/*       */     //   380: aload_0
/*       */     //   381: <illegal opcode> bbah : (Ljava/lang/Object;)V
/*       */     //   386: return
/*       */     //   387: aload_0
/*       */     //   388: aload_0
/*       */     //   389: getfield averageTransactionPing : I
/*       */     //   392: sipush #24555
/*       */     //   395: istore #16
/*       */     //   397: iload #16
/*       */     //   399: sipush #24559
/*       */     //   402: istore #18
/*       */     //   404: istore #17
/*       */     //   406: iload #17
/*       */     //   408: iload #18
/*       */     //   410: ior
/*       */     //   411: iload #17
/*       */     //   413: iconst_m1
/*       */     //   414: ixor
/*       */     //   415: iload #18
/*       */     //   417: iconst_m1
/*       */     //   418: ixor
/*       */     //   419: ior
/*       */     //   420: iand
/*       */     //   421: imul
/*       */     //   422: aload_0
/*       */     //   423: getfield transactionPing : I
/*       */     //   426: istore #18
/*       */     //   428: istore #17
/*       */     //   430: iload #17
/*       */     //   432: iload #18
/*       */     //   434: ior
/*       */     //   435: iload #17
/*       */     //   437: iload #18
/*       */     //   439: iand
/*       */     //   440: iadd
/*       */     //   441: sipush #1544
/*       */     //   444: istore #16
/*       */     //   446: ldc2_w 8492439036244264461
/*       */     //   449: l2i
/*       */     //   450: iload #16
/*       */     //   452: istore #18
/*       */     //   454: istore #17
/*       */     //   456: iload #17
/*       */     //   458: iload #18
/*       */     //   460: ior
/*       */     //   461: iload #17
/*       */     //   463: iconst_m1
/*       */     //   464: ixor
/*       */     //   465: iload #18
/*       */     //   467: iconst_m1
/*       */     //   468: ixor
/*       */     //   469: ior
/*       */     //   470: iand
/*       */     //   471: idiv
/*       */     //   472: putfield averageTransactionPing : I
/*       */     //   475: aload_0
/*       */     //   476: <illegal opcode> bbai : (Ljava/lang/Object;)V
/*       */     //   481: aload_0
/*       */     //   482: getfield pingQueue : Ljava/util/Queue;
/*       */     //   485: <illegal opcode> bbaj : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   490: checkcast java/util/function/BiConsumer
/*       */     //   493: dup
/*       */     //   494: astore #4
/*       */     //   496: ifnull -> 528
/*       */     //   499: aload #4
/*       */     //   501: aload_0
/*       */     //   502: getfield transactionPing : I
/*       */     //   505: <illegal opcode> bbak : (I)Ljava/lang/Integer;
/*       */     //   510: aload #5
/*       */     //   512: <illegal opcode> bbal : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   517: checkcast java/lang/Double
/*       */     //   520: <illegal opcode> bbam : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
/*       */     //   525: goto -> 481
/*       */     //   528: new java/util/ArrayList
/*       */     //   531: dup
/*       */     //   532: aload_0
/*       */     //   533: getfield reachData : Ljava/util/Queue;
/*       */     //   536: invokespecial <init> : (Ljava/util/Collection;)V
/*       */     //   539: astore #7
/*       */     //   541: aload_0
/*       */     //   542: getfield checkData : Lme/levansj01/fc;
/*       */     //   545: <illegal opcode> bban : (Ljava/lang/Object;)[Lme/levansj01/gq;
/*       */     //   550: checkcast [Lme/levansj01/gq;
/*       */     //   553: astore #8
/*       */     //   555: aload #8
/*       */     //   557: arraylength
/*       */     //   558: istore #9
/*       */     //   560: ldc2_w 1438123976059527147
/*       */     //   563: l2i
/*       */     //   564: sipush #8171
/*       */     //   567: istore #18
/*       */     //   569: istore #17
/*       */     //   571: iload #17
/*       */     //   573: iload #18
/*       */     //   575: ior
/*       */     //   576: iload #17
/*       */     //   578: iload #18
/*       */     //   580: iand
/*       */     //   581: iconst_m1
/*       */     //   582: ixor
/*       */     //   583: iand
/*       */     //   584: istore #10
/*       */     //   586: iload #10
/*       */     //   588: iload #9
/*       */     //   590: if_icmplt -> 596
/*       */     //   593: goto -> 732
/*       */     //   596: aload #8
/*       */     //   598: iload #10
/*       */     //   600: aaload
/*       */     //   601: astore #11
/*       */     //   603: aload #11
/*       */     //   605: aload #7
/*       */     //   607: aload_0
/*       */     //   608: getfield timestamp : J
/*       */     //   611: <illegal opcode> bbao : (Ljava/lang/Object;Ljava/util/List;J)V
/*       */     //   616: iinc #10, 1
/*       */     //   619: ldc2_w 3669082598379910736
/*       */     //   622: l2i
/*       */     //   623: ldc_w 861361770
/*       */     //   626: istore #18
/*       */     //   628: istore #17
/*       */     //   630: iload #17
/*       */     //   632: iload #18
/*       */     //   634: ior
/*       */     //   635: iload #17
/*       */     //   637: iload #18
/*       */     //   639: iand
/*       */     //   640: iconst_m1
/*       */     //   641: ixor
/*       */     //   642: iand
/*       */     //   643: istore #13
/*       */     //   645: iload #13
/*       */     //   647: putstatic me/levansj01/dc.dqw : I
/*       */     //   650: ldc_w 1796855196
/*       */     //   653: istore #16
/*       */     //   655: iload #16
/*       */     //   657: ldc2_w -1884375136858002623
/*       */     //   660: l2i
/*       */     //   661: istore #18
/*       */     //   663: istore #17
/*       */     //   665: iload #17
/*       */     //   667: iload #18
/*       */     //   669: ior
/*       */     //   670: iload #17
/*       */     //   672: iload #18
/*       */     //   674: iand
/*       */     //   675: iconst_m1
/*       */     //   676: ixor
/*       */     //   677: iand
/*       */     //   678: getstatic me/levansj01/dc.dqw : I
/*       */     //   681: if_icmple -> 687
/*       */     //   684: goto -> 586
/*       */     //   687: getstatic me/levansj01/dc.dqx : Z
/*       */     //   690: ifne -> 696
/*       */     //   693: goto -> 586
/*       */     //   696: ldc2_w 6226078458791869116
/*       */     //   699: l2i
/*       */     //   700: ldc_w 549162499
/*       */     //   703: istore #18
/*       */     //   705: istore #17
/*       */     //   707: iload #17
/*       */     //   709: iload #18
/*       */     //   711: iconst_m1
/*       */     //   712: ixor
/*       */     //   713: iand
/*       */     //   714: iload #17
/*       */     //   716: iconst_m1
/*       */     //   717: ixor
/*       */     //   718: iload #18
/*       */     //   720: iand
/*       */     //   721: ior
/*       */     //   722: istore #13
/*       */     //   724: iload #13
/*       */     //   726: putstatic me/levansj01/dc.dqw : I
/*       */     //   729: goto -> 586
/*       */     //   732: aload_0
/*       */     //   733: getfield reachData : Ljava/util/Queue;
/*       */     //   736: <illegal opcode> bbap : (Ljava/lang/Object;)V
/*       */     //   741: aload_0
/*       */     //   742: getfield checkData : Lme/levansj01/fc;
/*       */     //   745: <illegal opcode> bbaq : (Ljava/lang/Object;)[Lme/levansj01/hw;
/*       */     //   750: checkcast [Lme/levansj01/hw;
/*       */     //   753: astore #8
/*       */     //   755: aload #8
/*       */     //   757: arraylength
/*       */     //   758: istore #9
/*       */     //   760: sipush #9818
/*       */     //   763: istore #16
/*       */     //   765: iload #16
/*       */     //   767: sipush #9818
/*       */     //   770: istore #18
/*       */     //   772: istore #17
/*       */     //   774: iload #17
/*       */     //   776: iload #18
/*       */     //   778: ior
/*       */     //   779: iload #17
/*       */     //   781: iload #18
/*       */     //   783: iand
/*       */     //   784: isub
/*       */     //   785: istore #10
/*       */     //   787: iload #10
/*       */     //   789: iload #9
/*       */     //   791: if_icmplt -> 797
/*       */     //   794: goto -> 832
/*       */     //   797: aload #8
/*       */     //   799: iload #10
/*       */     //   801: aaload
/*       */     //   802: astore #11
/*       */     //   804: aload #11
/*       */     //   806: aload_3
/*       */     //   807: <illegal opcode> bbar : (Ljava/lang/Object;)Ljava/lang/Long;
/*       */     //   812: <illegal opcode> bbas : (Ljava/lang/Object;)J
/*       */     //   817: aload_0
/*       */     //   818: getfield timestamp : J
/*       */     //   821: <illegal opcode> bbat : (Ljava/lang/Object;JJ)V
/*       */     //   826: iinc #10, 1
/*       */     //   829: goto -> 787
/*       */     //   832: goto -> 913
/*       */     //   835: aload_0
/*       */     //   836: getfield checkData : Lme/levansj01/fc;
/*       */     //   839: <illegal opcode> bbau : (Ljava/lang/Object;)Lme/levansj01/cj;
/*       */     //   844: ifnull -> 913
/*       */     //   847: aload_0
/*       */     //   848: getfield totalTicks : I
/*       */     //   851: sipush #13090
/*       */     //   854: istore #16
/*       */     //   856: iload #16
/*       */     //   858: sipush #12978
/*       */     //   861: istore #18
/*       */     //   863: istore #17
/*       */     //   865: iload #17
/*       */     //   867: iload #18
/*       */     //   869: ior
/*       */     //   870: iload #17
/*       */     //   872: iconst_m1
/*       */     //   873: ixor
/*       */     //   874: iload #18
/*       */     //   876: iconst_m1
/*       */     //   877: ixor
/*       */     //   878: ior
/*       */     //   879: iand
/*       */     //   880: if_icmpgt -> 886
/*       */     //   883: goto -> 913
/*       */     //   886: aload_0
/*       */     //   887: getfield inventoryOpen : Z
/*       */     //   890: ifeq -> 896
/*       */     //   893: goto -> 913
/*       */     //   896: <illegal opcode> bbav : ()Lme/levansj01/verus/compat/NMSManager;
/*       */     //   901: aload_0
/*       */     //   902: iload_2
/*       */     //   903: <illegal opcode> run : (Lme/levansj01/dc;S)Ljava/lang/Runnable;
/*       */     //   908: <illegal opcode> bbaw : (Ljava/lang/Object;Ljava/lang/Runnable;)V
/*       */     //   913: aload_0
/*       */     //   914: aload_0
/*       */     //   915: getfield timestamp : J
/*       */     //   918: putfield lastClientTransaction : J
/*       */     //   921: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #30471	-> 0
/*       */     //   #50584	-> 7
/*       */     //   #47098	-> 22
/*       */     //   #40103	-> 41
/*       */     //   #47851	-> 57
/*       */     //   #29525	-> 62
/*       */     //   #51038	-> 96
/*       */     //   #17465	-> 107
/*       */     //   #43316	-> 154
/*       */     //   #4526	-> 169
/*       */     //   #46844	-> 182
/*       */     //   #51087	-> 190
/*       */     //   #54625	-> 205
/*       */     //   #42566	-> 217
/*       */     //   #2910	-> 258
/*       */     //   #4023	-> 380
/*       */     //   #9652	-> 386
/*       */     //   #13815	-> 387
/*       */     //   #59329	-> 475
/*       */     //   #50721	-> 481
/*       */     //   #3000	-> 499
/*       */     //   #4681	-> 528
/*       */     //   #56063	-> 541
/*       */     //   #26205	-> 603
/*       */     //   #35266	-> 616
/*       */     //   #32392	-> 732
/*       */     //   #26890	-> 741
/*       */     //   #3624	-> 804
/*       */     //   #61487	-> 826
/*       */     //   #29972	-> 832
/*       */     //   #35233	-> 896
/*       */     //   #22935	-> 913
/*       */     //   #34211	-> 921
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   603	13	11	brrs	Lme/levansj01/gq;
/*       */     //   804	22	11	brrt	Lme/levansj01/hw;
/*       */     //   496	336	4	brru	Ljava/util/function/BiConsumer;
/*       */     //   182	650	5	brrv	Lme/levansj01/iu;
/*       */     //   217	615	6	brrw	Lme/levansj01/kh;
/*       */     //   541	291	7	brrx	Ljava/util/ArrayList;
/*       */     //   0	922	0	brry	Lme/levansj01/dc;
/*       */     //   0	922	1	brrz	Lme/levansj01/ed;
/*       */     //   7	915	2	brsa	S
/*       */     //   41	881	3	brsb	Lme/levansj01/df;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public lh<Integer> getJumpLevel() {
/*       */     return this.jumpLevel;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void postProcess(ll<?> brxq) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield checkData : Lme/levansj01/fc;
/*       */     //   4: <illegal opcode> bbpb : (Ljava/lang/Object;)[Lme/levansj01/cv;
/*       */     //   9: checkcast [Lme/levansj01/cv;
/*       */     //   12: astore_3
/*       */     //   13: aload_3
/*       */     //   14: arraylength
/*       */     //   15: istore #4
/*       */     //   17: sipush #7015
/*       */     //   20: ldc2_w 7366584020399823719
/*       */     //   23: l2i
/*       */     //   24: istore #13
/*       */     //   26: istore #12
/*       */     //   28: iload #12
/*       */     //   30: iload #13
/*       */     //   32: ior
/*       */     //   33: iload #12
/*       */     //   35: iload #13
/*       */     //   37: iand
/*       */     //   38: iconst_m1
/*       */     //   39: ixor
/*       */     //   40: iand
/*       */     //   41: istore #5
/*       */     //   43: iload #5
/*       */     //   45: iload #4
/*       */     //   47: if_icmplt -> 53
/*       */     //   50: goto -> 309
/*       */     //   53: aload_3
/*       */     //   54: iload #5
/*       */     //   56: aaload
/*       */     //   57: astore #6
/*       */     //   59: aload #6
/*       */     //   61: aload_1
/*       */     //   62: aload_0
/*       */     //   63: getfield timestamp : J
/*       */     //   66: <illegal opcode> bbpc : (Ljava/lang/Object;Lme/levansj01/ll;J)V
/*       */     //   71: goto -> 303
/*       */     //   74: astore #7
/*       */     //   76: <illegal opcode> bbpd : ()Ljava/util/logging/Logger;
/*       */     //   81: getstatic java/util/logging/Level.WARNING : Ljava/util/logging/Level;
/*       */     //   84: new java/lang/StringBuilder
/*       */     //   87: dup
/*       */     //   88: invokespecial <init> : ()V
/*       */     //   91: aload #6
/*       */     //   93: <illegal opcode> bbpe : (Ljava/lang/Object;)Lme/levansj01/j;
/*       */     //   98: <illegal opcode> bbpf : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   103: <illegal opcode> bbpg : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   108: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   111: sipush #28936
/*       */     //   114: istore #11
/*       */     //   116: iload #11
/*       */     //   118: sipush #29006
/*       */     //   121: istore #13
/*       */     //   123: istore #12
/*       */     //   125: iload #12
/*       */     //   127: iload #13
/*       */     //   129: iconst_m1
/*       */     //   130: ixor
/*       */     //   131: iand
/*       */     //   132: iload #12
/*       */     //   134: iconst_m1
/*       */     //   135: ixor
/*       */     //   136: iload #13
/*       */     //   138: iand
/*       */     //   139: ior
/*       */     //   140: aaload
/*       */     //   141: <illegal opcode> bbph : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   146: aload #6
/*       */     //   148: <illegal opcode> bbpi : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   153: <illegal opcode> bbpj : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   158: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   161: ldc2_w 5845177256921543346
/*       */     //   164: l2i
/*       */     //   165: sipush #13045
/*       */     //   168: istore #13
/*       */     //   170: istore #12
/*       */     //   172: iload #12
/*       */     //   174: iload #13
/*       */     //   176: ior
/*       */     //   177: iload #12
/*       */     //   179: iconst_m1
/*       */     //   180: ixor
/*       */     //   181: iload #13
/*       */     //   183: iconst_m1
/*       */     //   184: ixor
/*       */     //   185: ior
/*       */     //   186: iand
/*       */     //   187: aaload
/*       */     //   188: <illegal opcode> bbpk : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   193: aload #6
/*       */     //   195: <illegal opcode> bbpl : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   200: <illegal opcode> bbpm : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   205: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   208: sipush #15571
/*       */     //   211: istore #11
/*       */     //   213: iload #11
/*       */     //   215: ldc2_w 384448999475657883
/*       */     //   218: l2i
/*       */     //   219: istore #13
/*       */     //   221: istore #12
/*       */     //   223: iload #12
/*       */     //   225: iload #13
/*       */     //   227: ior
/*       */     //   228: iload #12
/*       */     //   230: iload #13
/*       */     //   232: iand
/*       */     //   233: isub
/*       */     //   234: aaload
/*       */     //   235: <illegal opcode> bbpn : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   240: aload_1
/*       */     //   241: <illegal opcode> bbpo : (Ljava/lang/Object;)Ljava/lang/Class;
/*       */     //   246: <illegal opcode> bbpp : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   251: <illegal opcode> bbpq : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   256: getstatic me/levansj01/dc.uw : [Ljava/lang/String;
/*       */     //   259: sipush #20908
/*       */     //   262: ldc2_w -8154669339069361691
/*       */     //   265: l2i
/*       */     //   266: istore #13
/*       */     //   268: istore #12
/*       */     //   270: iload #12
/*       */     //   272: iload #13
/*       */     //   274: ior
/*       */     //   275: iload #12
/*       */     //   277: iconst_m1
/*       */     //   278: ixor
/*       */     //   279: iload #13
/*       */     //   281: iconst_m1
/*       */     //   282: ixor
/*       */     //   283: ior
/*       */     //   284: iand
/*       */     //   285: aaload
/*       */     //   286: <illegal opcode> bbpr : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/StringBuilder;
/*       */     //   291: <illegal opcode> bbps : (Ljava/lang/Object;)Ljava/lang/String;
/*       */     //   296: aload #7
/*       */     //   298: <illegal opcode> bbpt : (Ljava/lang/Object;Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
/*       */     //   303: iinc #5, 1
/*       */     //   306: goto -> 43
/*       */     //   309: aload_1
/*       */     //   310: instanceof me/levansj01/l
/*       */     //   313: ifne -> 319
/*       */     //   316: goto -> 350
/*       */     //   319: aload_1
/*       */     //   320: checkcast me/levansj01/l
/*       */     //   323: dup
/*       */     //   324: astore_2
/*       */     //   325: <illegal opcode> bbpu : (Ljava/lang/Object;)Z
/*       */     //   330: ifeq -> 336
/*       */     //   333: goto -> 350
/*       */     //   336: aload_0
/*       */     //   337: getfield end : Ljava/util/Queue;
/*       */     //   340: <illegal opcode> get : ()Ljava/util/function/Supplier;
/*       */     //   345: <illegal opcode> bbpv : (Ljava/util/Queue;Ljava/util/function/Supplier;)V
/*       */     //   350: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #30623	-> 0
/*       */     //   #48440	-> 59
/*       */     //   #10278	-> 71
/*       */     //   #37329	-> 74
/*       */     //   #60672	-> 76
/*       */     //   #15244	-> 303
/*       */     //   #9576	-> 309
/*       */     //   #33887	-> 336
/*       */     //   #37463	-> 350
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   325	25	2	brxo	Lme/levansj01/l;
/*       */     //   0	351	1	brxq	Lme/levansj01/ll;
/*       */     //   76	227	7	brxm	Ljava/lang/Throwable;
/*       */     //   0	351	0	brxp	Lme/levansj01/dc;
/*       */     //   59	244	6	brxn	Lme/levansj01/cv;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	351	1	brxq	Lme/levansj01/ll<*>;
/*       */     // Exception table:
/*       */     //   from	to	target	type
/*       */     //   59	71	74	java/lang/Throwable
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isSlimePush() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   4: getstatic me/levansj01/ij.SLIME_PUSH : Lme/levansj01/ij;
/*       */     //   7: <illegal opcode> bbvl : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   12: aload_0
/*       */     //   13: <illegal opcode> bbvm : (Ljava/lang/Object;)I
/*       */     //   18: ldc2_w 6966695665762060988
/*       */     //   21: l2i
/*       */     //   22: sipush #19134
/*       */     //   25: istore #6
/*       */     //   27: istore #5
/*       */     //   29: iload #5
/*       */     //   31: iload #6
/*       */     //   33: ior
/*       */     //   34: iload #5
/*       */     //   36: iconst_m1
/*       */     //   37: ixor
/*       */     //   38: iload #6
/*       */     //   40: iconst_m1
/*       */     //   41: ixor
/*       */     //   42: ior
/*       */     //   43: iand
/*       */     //   44: istore #6
/*       */     //   46: istore #5
/*       */     //   48: iload #5
/*       */     //   50: iload #6
/*       */     //   52: iconst_m1
/*       */     //   53: ixor
/*       */     //   54: isub
/*       */     //   55: iconst_1
/*       */     //   56: isub
/*       */     //   57: sipush #10320
/*       */     //   60: istore #4
/*       */     //   62: iload #4
/*       */     //   64: sipush #10322
/*       */     //   67: istore #6
/*       */     //   69: istore #5
/*       */     //   71: iload #5
/*       */     //   73: iload #6
/*       */     //   75: iconst_m1
/*       */     //   76: ixor
/*       */     //   77: iand
/*       */     //   78: iload #5
/*       */     //   80: iconst_m1
/*       */     //   81: ixor
/*       */     //   82: iload #6
/*       */     //   84: iand
/*       */     //   85: ior
/*       */     //   86: imul
/*       */     //   87: if_icmplt -> 93
/*       */     //   90: goto -> 121
/*       */     //   93: sipush #30303
/*       */     //   96: sipush #30302
/*       */     //   99: istore #6
/*       */     //   101: istore #5
/*       */     //   103: iload #5
/*       */     //   105: iload #6
/*       */     //   107: ior
/*       */     //   108: iload #5
/*       */     //   110: iconst_m1
/*       */     //   111: ixor
/*       */     //   112: iload #6
/*       */     //   114: iconst_m1
/*       */     //   115: ixor
/*       */     //   116: ior
/*       */     //   117: iand
/*       */     //   118: goto -> 147
/*       */     //   121: ldc2_w -5024789931600358362
/*       */     //   124: l2i
/*       */     //   125: sipush #21542
/*       */     //   128: istore #6
/*       */     //   130: istore #5
/*       */     //   132: iload #5
/*       */     //   134: iload #6
/*       */     //   136: ior
/*       */     //   137: iload #5
/*       */     //   139: iconst_m1
/*       */     //   140: ixor
/*       */     //   141: iload #6
/*       */     //   143: iconst_m1
/*       */     //   144: ixor
/*       */     //   145: ior
/*       */     //   146: iand
/*       */     //   147: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #31149	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	148	0	bsbd	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(oo<?> brwk) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield world : Lme/levansj01/f;
/*       */     //   4: ifnull -> 17
/*       */     //   7: aload_0
/*       */     //   8: getfield world : Lme/levansj01/f;
/*       */     //   11: aload_1
/*       */     //   12: <illegal opcode> bbnt : (Ljava/lang/Object;Lme/levansj01/oo;)V
/*       */     //   17: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #31378	-> 0
/*       */     //   #16691	-> 7
/*       */     //   #23317	-> 17
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	18	0	brwj	Lme/levansj01/dc;
/*       */     //   0	18	1	brwk	Lme/levansj01/oo;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	18	1	brwk	Lme/levansj01/oo<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void handle(ic<?> brso) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: sipush #13406
/*       */     //   4: sipush #13406
/*       */     //   7: istore #7
/*       */     //   9: istore #6
/*       */     //   11: iload #6
/*       */     //   13: iload #7
/*       */     //   15: ior
/*       */     //   16: iload #6
/*       */     //   18: iconst_m1
/*       */     //   19: ixor
/*       */     //   20: iload #7
/*       */     //   22: iconst_m1
/*       */     //   23: ixor
/*       */     //   24: ior
/*       */     //   25: iand
/*       */     //   26: putfield blocking : Z
/*       */     //   29: aload_1
/*       */     //   30: <illegal opcode> bbcf : (Ljava/lang/Object;)Lme/levansj01/da;
/*       */     //   35: getstatic me/levansj01/da.OPEN_INVENTORY_ACHIEVEMENT : Lme/levansj01/da;
/*       */     //   38: if_acmpeq -> 44
/*       */     //   41: goto -> 82
/*       */     //   44: aload_0
/*       */     //   45: sipush #22905
/*       */     //   48: istore #5
/*       */     //   50: iload #5
/*       */     //   52: ldc2_w 2645672920596109688
/*       */     //   55: l2i
/*       */     //   56: istore #7
/*       */     //   58: istore #6
/*       */     //   60: iload #6
/*       */     //   62: iload #7
/*       */     //   64: ior
/*       */     //   65: iload #6
/*       */     //   67: iload #7
/*       */     //   69: iand
/*       */     //   70: isub
/*       */     //   71: putfield inventoryOpen : Z
/*       */     //   74: aload_0
/*       */     //   75: aload_0
/*       */     //   76: getfield totalTicks : I
/*       */     //   79: putfield lastInventoryTick : I
/*       */     //   82: return
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #31398	-> 0
/*       */     //   #2913	-> 29
/*       */     //   #23000	-> 44
/*       */     //   #20915	-> 74
/*       */     //   #51257	-> 82
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	83	0	brsn	Lme/levansj01/dc;
/*       */     //   0	83	1	brso	Lme/levansj01/ic;
/*       */     // Local variable type table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	83	1	brso	Lme/levansj01/ic<*>;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isSwingDigging() {
/*       */     return this.swingDigging;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void setLastVelX(double bsiv) {
/*       */     this.lastVelX = bsiv;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Map<Long, Long> getKeepAliveMap() {
/*       */     return this.keepAliveMap;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean shouldHaveReceivedPing() {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield shouldHaveReceivedPing : Lme/levansj01/lh;
/*       */     //   4: <illegal opcode> bbua : (Ljava/lang/Object;)Ljava/lang/Object;
/*       */     //   9: checkcast java/lang/Boolean
/*       */     //   12: <illegal opcode> bbub : (Ljava/lang/Object;)Z
/*       */     //   17: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #32484	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	18	0	brzv	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public gz getLocation() {
/* 32609 */     return this.location;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public boolean isSurvival(int bsbi) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield survivalTicks : I
/*       */     //   4: aload_0
/*       */     //   5: <illegal opcode> bbvt : (Ljava/lang/Object;)I
/*       */     //   10: iload_1
/*       */     //   11: imul
/*       */     //   12: if_icmpgt -> 18
/*       */     //   15: goto -> 41
/*       */     //   18: ldc2_w 6850328131081879056
/*       */     //   21: l2i
/*       */     //   22: sipush #15889
/*       */     //   25: istore_3
/*       */     //   26: istore_2
/*       */     //   27: iload_2
/*       */     //   28: iload_3
/*       */     //   29: ior
/*       */     //   30: iload_2
/*       */     //   31: iconst_m1
/*       */     //   32: ixor
/*       */     //   33: iload_3
/*       */     //   34: iconst_m1
/*       */     //   35: ixor
/*       */     //   36: ior
/*       */     //   37: iand
/*       */     //   38: goto -> 57
/*       */     //   41: sipush #16588
/*       */     //   44: ldc2_w -8797973264400891700
/*       */     //   47: l2i
/*       */     //   48: istore_3
/*       */     //   49: istore_2
/*       */     //   50: iload_2
/*       */     //   51: iload_3
/*       */     //   52: ior
/*       */     //   53: iload_2
/*       */     //   54: iload_3
/*       */     //   55: iand
/*       */     //   56: isub
/*       */     //   57: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #32720	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	58	0	bsbh	Lme/levansj01/dc;
/*       */     //   0	58	1	bsbi	I
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean hasPlacedBlock(boolean bsbs) {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: getfield tickerMap : Lme/levansj01/jz;
/*       */     //   4: getstatic me/levansj01/ij.PLACED_BLOCK_UNDER : Lme/levansj01/ij;
/*       */     //   7: <illegal opcode> bbwi : (Ljava/lang/Object;Lme/levansj01/ij;)I
/*       */     //   12: aload_0
/*       */     //   13: <illegal opcode> bbwj : (Ljava/lang/Object;)I
/*       */     //   18: sipush #25985
/*       */     //   21: sipush #25984
/*       */     //   24: istore #7
/*       */     //   26: istore #6
/*       */     //   28: iload #6
/*       */     //   30: iload #7
/*       */     //   32: ior
/*       */     //   33: iload #6
/*       */     //   35: iload #7
/*       */     //   37: iand
/*       */     //   38: iconst_m1
/*       */     //   39: ixor
/*       */     //   40: iand
/*       */     //   41: istore #7
/*       */     //   43: istore #6
/*       */     //   45: iload #6
/*       */     //   47: iload #7
/*       */     //   49: ixor
/*       */     //   50: iconst_2
/*       */     //   51: iload #6
/*       */     //   53: iload #7
/*       */     //   55: iand
/*       */     //   56: imul
/*       */     //   57: iadd
/*       */     //   58: sipush #3049
/*       */     //   61: istore #5
/*       */     //   63: iload #5
/*       */     //   65: sipush #3051
/*       */     //   68: istore #7
/*       */     //   70: istore #6
/*       */     //   72: iload #6
/*       */     //   74: iload #7
/*       */     //   76: ior
/*       */     //   77: iload #6
/*       */     //   79: iload #7
/*       */     //   81: iand
/*       */     //   82: iconst_m1
/*       */     //   83: ixor
/*       */     //   84: iand
/*       */     //   85: imul
/*       */     //   86: if_icmplt -> 92
/*       */     //   89: goto -> 120
/*       */     //   92: sipush #28882
/*       */     //   95: sipush #28883
/*       */     //   98: istore #7
/*       */     //   100: istore #6
/*       */     //   102: iload #6
/*       */     //   104: iload #7
/*       */     //   106: ior
/*       */     //   107: iload #6
/*       */     //   109: iconst_m1
/*       */     //   110: ixor
/*       */     //   111: iload #7
/*       */     //   113: iconst_m1
/*       */     //   114: ixor
/*       */     //   115: ior
/*       */     //   116: iand
/*       */     //   117: goto -> 150
/*       */     //   120: sipush #9203
/*       */     //   123: istore #5
/*       */     //   125: ldc2_w 702938399480226803
/*       */     //   128: l2i
/*       */     //   129: iload #5
/*       */     //   131: istore #7
/*       */     //   133: istore #6
/*       */     //   135: iload #6
/*       */     //   137: iload #7
/*       */     //   139: ior
/*       */     //   140: iload #6
/*       */     //   142: iconst_m1
/*       */     //   143: ixor
/*       */     //   144: iload #7
/*       */     //   146: iconst_m1
/*       */     //   147: ixor
/*       */     //   148: ior
/*       */     //   149: iand
/*       */     //   150: ireturn
/*       */     // Line number table:
/*       */     //   Java source line number -> byte code offset
/*       */     //   #32806	-> 0
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	descriptor
/*       */     //   0	151	1	bsbs	Z
/*       */     //   0	151	0	bsbr	Lme/levansj01/dc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public long getLastFast() {
/* 32935 */     return this.lastFast;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static Object cxe(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
/*       */     MethodHandle methodHandle;
/* 33241 */     byte[] arrayOfByte1 = Base64.getDecoder().decode((String)paramObject4);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 41954 */     byte[] arrayOfByte2 = new byte[arrayOfByte1.length];
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     short s = 17695;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 

