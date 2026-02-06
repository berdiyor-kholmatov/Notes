package com.example.listofnotes.repo.pagingSource
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.listofnotes.domain.model.Note
//import com.example.listofnotes.repo.notesRepo.NotesRepository
//
//class NotesPagingSource(repository: NotesRepository): PagingSource<Int, Note>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Note> {
//        val key = params.key ?: 1
//        try {
//            //let request: List<NoteTitle> = repository.getNotes(key)
//
//
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
//
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Note>): Int? {
//        return state.anchorPosition?.let { position ->
//            val page = state.closestPageToPosition(position)
//            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
//        }
//    }
//}