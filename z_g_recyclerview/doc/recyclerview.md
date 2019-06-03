
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


#onMeasure 调用
at com.zeus.source.recyclerview.RecyclerMainActivity$MyLayout.onMeasure(RecyclerMainActivity.java:112)
at android.view.View.measure(View.java:19857)
at com.zeus.source.recyclerview.origin.RecyclerView$LayoutManager.measureChildWithMargins(RecyclerView.java:9173)
at com.zeus.source.recyclerview.origin.LinearLayoutManager.layoutChunk(LinearLayoutManager.java:1583)
at com.zeus.source.recyclerview.origin.LinearLayoutManager.fill(LinearLayoutManager.java:1517)
at com.zeus.source.recyclerview.origin.LinearLayoutManager.onLayoutChildren(LinearLayoutManager.java:612)
at com.zeus.source.recyclerview.origin.RecyclerView.dispatchLayoutStep2(RecyclerView.java:3997)
at com.zeus.source.recyclerview.origin.RecyclerView.dispatchLayout(RecyclerView.java:3711)
at com.zeus.source.recyclerview.origin.RecyclerView.onLayout(RecyclerView.java:4273)
at android.view.View.layout(View.java:17637)


#onLayout 调用
at com.zeus.source.recyclerview.RecyclerMainActivity$MyLayout.onLayout(RecyclerMainActivity.java:118)
at android.view.View.layout(View.java:17637)
at android.view.ViewGroup.layout(ViewGroup.java:5575)
at com.zeus.source.recyclerview.origin.RecyclerView$LayoutManager.layoutDecoratedWithMargins(RecyclerView.java:9371)
at com.zeus.source.recyclerview.origin.LinearLayoutManager.layoutChunk(LinearLayoutManager.java:1615)
at com.zeus.source.recyclerview.origin.LinearLayoutManager.fill(LinearLayoutManager.java:1517)
at com.zeus.source.recyclerview.origin.LinearLayoutManager.onLayoutChildren(LinearLayoutManager.java:612)
at com.zeus.source.recyclerview.origin.RecyclerView.dispatchLayoutStep2(RecyclerView.java:3997)
at com.zeus.source.recyclerview.origin.RecyclerView.dispatchLayout(RecyclerView.java:3711)
at com.zeus.source.recyclerview.origin.RecyclerView.onLayout(RecyclerView.java:4273)
at android.view.View.layout(View.java:17637)



