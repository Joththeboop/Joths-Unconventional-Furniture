package net.jothb.jufurniture.juf.block.blocks;

import net.jothb.jufurniture.juf.block.enums.CustomBlockHalf;
import net.jothb.state.property.ModProperties;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class TripleDoor extends Block {

    public static final EnumProperty<CustomBlockHalf> HALF = ModProperties.CUSTOM_BLOCK_HALF;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty OPEN = Properties.OPEN;
    public static final EnumProperty<DoorHinge> HINGE = Properties.DOOR_HINGE;
    public static final BooleanProperty POWERED = Properties.POWERED;
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);

    public TripleDoor(Settings settings) {
        super(settings);
        setDefaultState((this.stateManager.getDefaultState().with(HALF, CustomBlockHalf.LOWER).with(FACING, Direction.NORTH).with(OPEN, false)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        boolean bl = !state.get(OPEN);
        boolean bl2 = state.get(HINGE) == DoorHinge.RIGHT;
        switch (direction) {
            default: {
                return bl ? WEST_SHAPE : (bl2 ? SOUTH_SHAPE : NORTH_SHAPE);
            }
            case SOUTH: {
                return bl ? NORTH_SHAPE : (bl2 ? WEST_SHAPE : EAST_SHAPE);
            }
            case WEST: {
                return bl ? EAST_SHAPE : (bl2 ? NORTH_SHAPE : SOUTH_SHAPE);
            }
            case NORTH:
        }
        return bl ? SOUTH_SHAPE : (bl2 ? EAST_SHAPE : WEST_SHAPE);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        CustomBlockHalf customBlockHalf = state.get(HALF);
        if (direction.getAxis() == Direction.Axis.Y && ((customBlockHalf == CustomBlockHalf.LOWER && (direction == Direction.UP) ||
                (customBlockHalf == CustomBlockHalf.UPPER && (direction == Direction.DOWN)) || customBlockHalf == CustomBlockHalf.MIDDLE))) {
            if (neighborState.isOf(this)) {
                return (BlockState)state.with(FACING, neighborState.get(FACING)).with(OPEN, neighborState.get(OPEN)).with(HINGE, neighborState.get(HINGE)).with(POWERED,neighborState.get(POWERED));
            }
            return Blocks.AIR.getDefaultState();
        }
        if (customBlockHalf == CustomBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        if (blockPos.getY() < world.getTopY() - 1 && world.getBlockState(blockPos.up()).canReplace(ctx) && world.getBlockState(blockPos.up(1)).canReplace(ctx)) {
            boolean bl = world.isReceivingRedstonePower(blockPos) || world.isReceivingRedstonePower(blockPos.up());
            return (BlockState)this.getDefaultState().with(HALF, CustomBlockHalf.LOWER).with(FACING, ctx.getPlayerFacing()).with(HINGE,this.getHinge(ctx));
        }
        return null;
    }

    private DoorHinge getHinge(ItemPlacementContext ctx) {
        boolean bl2;
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction direction = ctx.getPlayerFacing();
        BlockPos blockPos2 = blockPos.up();
        Direction direction2 = direction.rotateYCounterclockwise();
        BlockPos blockPos3 = blockPos.offset(direction2);
        BlockState blockState = blockView.getBlockState(blockPos3);
        BlockPos blockPos4 = blockPos2.offset(direction2);
        BlockState blockState2 = blockView.getBlockState(blockPos4);
        Direction direction3 = direction.rotateYClockwise();
        BlockPos blockPos5 = blockPos.offset(direction3);
        BlockState blockState3 = blockView.getBlockState(blockPos5);
        BlockPos blockPos6 = blockPos2.offset(direction3);
        BlockState blockState4 = blockView.getBlockState(blockPos6);
        int i = (blockState.isFullCube(blockView, blockPos3) ? -1 : 0) + (blockState2.isFullCube(blockView, blockPos4) ? -1 : 0) + (blockState3.isFullCube(blockView, blockPos5) ? 1 : 0) + (blockState4.isFullCube(blockView, blockPos6) ? 1 : 0);
        boolean bl = blockState.isOf(this) && blockState.get(HALF) == CustomBlockHalf.LOWER;
        boolean bl3 = bl2 = blockState3.isOf(this) && blockState3.get(HALF) == CustomBlockHalf.LOWER;
        if (bl && !bl2 || i > 0) {
            return DoorHinge.RIGHT;
        }
        if (bl2 && !bl || i < 0) {
            return DoorHinge.LEFT;
        }
        int j = direction.getOffsetX();
        int k = direction.getOffsetZ();
        Vec3d vec3d = ctx.getHitPos();
        double d = vec3d.x - (double)blockPos.getX();
        double e = vec3d.z - (double)blockPos.getZ();
        return j < 0 && e < 0.5 || j > 0 && e > 0.5 || k < 0 && d > 0.5 || k > 0 && d < 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        if (mirror == BlockMirror.NONE) {
            return state;
        }
        return (BlockState)state.rotate(mirror.getRotation(state.get(FACING))).cycle(HINGE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        if (this.material == Material.METAL) {
            return ActionResult.PASS;
        }
        state = (BlockState)state.cycle(OPEN);
        world.setBlockState(pos, state, Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
        world.syncWorldEvent(player, state.get(OPEN) != false ? this.getOpenSoundEventId() : this.getCloseSoundEventId(), pos, 0);
        world.emitGameEvent((Entity)player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);

        return ActionResult.success(world.isClient);
    }

    public boolean isOpen(BlockState state) {
        return state.get(OPEN);
    }

    public void setOpen(@Nullable Entity entity, World world, BlockState state, BlockPos pos, boolean open) {
        if (!state.isOf(this) || state.get(OPEN) == open) {
            return;
        }
        world.setBlockState(pos, (BlockState)state.with(OPEN, open), Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
        this.playOpenCloseSound(world, pos, open);
        world.emitGameEvent(entity, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    private void playOpenCloseSound(World world, BlockPos pos, boolean open) {
        world.syncWorldEvent(null, open ? this.getOpenSoundEventId() : this.getCloseSoundEventId(), pos, 0);
    }

    private int getCloseSoundEventId() {
        return this.material == Material.METAL ? WorldEvents.IRON_DOOR_CLOSES : WorldEvents.WOODEN_DOOR_CLOSES;
    }

    private int getOpenSoundEventId() {
        return this.material == Material.METAL ? WorldEvents.IRON_DOOR_OPENS : WorldEvents.WOODEN_DOOR_OPENS;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), (BlockState)state.with(HALF,CustomBlockHalf.MIDDLE), Block.NOTIFY_ALL);
        world.setBlockState(pos.up(2), (BlockState)state.with(HALF,CustomBlockHalf.UPPER), Block.NOTIFY_ALL);  //!!!!
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, OPEN, HINGE, POWERED);
    }
}
