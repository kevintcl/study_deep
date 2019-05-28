
https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-1.0-dev 
tree	7c57d603f84f733ea4e5db57882b30004c4a4a70


#onCreateViewHolder 的调用

RecyclerView.onMeasure ->
RecyclerView.dispatchLayoutStep2() ->
LinearLayoutManager.onLayoutChildren(Recycler recycler, State state) ->
LinearLayoutManager.fill(RecyclerView.Recycler recycler, LayoutState layoutState,
                                    RecyclerView.State state, boolean stopOnFocusable) ->
LinearLayoutManager.layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state,
                                           LayoutState layoutState, LayoutChunkResult result) ->
                                           
LinearLayoutManager$LayoutState.next(RecyclerView.Recycler recycler) ->
Recycler.getViewForPosition(int position)  ->
Recycler.getViewForPosition(int position, boolean dryRun) ->
Recycler.tryGetViewHolderForPositionByDeadline(int position,boolean dryRun, long deadlineNs)  ->
Adapter.createViewHolder(@NonNull ViewGroup parent, int viewType)  ->

#onBindViewHolder 的调用
