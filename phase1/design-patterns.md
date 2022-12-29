# List of deisgn patterns which were used

1. Strategy pattern: Used when a IGraphGenerator is passed into level. Also used when IDoorDrawer, IPlayerDrawer, IGunDrawer, etc. are passed into Door, Player, Gun, etc. respectivly.

2. Abstract Factory pattern: IPresenter acts as a factory for IDoorDrawer, IPlayerDrawer, IGunDrawer, etc.

3. Observer pattern: Player stores a collection of IPlayerObserver instances and calls a method on those instances to update their target location (where the player is located).
