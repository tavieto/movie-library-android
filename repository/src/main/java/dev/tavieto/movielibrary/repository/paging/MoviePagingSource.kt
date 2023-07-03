package dev.tavieto.movielibrary.repository.paging

class MoviePagingSource /*: PagingSource<Int, MovieData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        val start = params.key ?: STARTING_KEY
        val range = start.until(start + params.loadSize)

        return LoadResult.Page(
            data = range.map { number ->
//                MovieData()
            },
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
//        val movie
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    private companion object {
        const val STARTING_KEY = 1
    }
}*/