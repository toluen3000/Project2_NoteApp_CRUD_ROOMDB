package com.example.project2_noteapp_crud

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDAO {
    //DAO mean data access object
    //cac phuong thuc trong sqlite
    //suspend Tạm ngừng hoạt động ứng dụng là chặn ứng dụng chạy trong nền
    // và sử dụng tài nguyên hệ thống (như pin và dữ liệu) khi bạn không sử dụng ứng dụng đó.
    @Insert
    suspend fun insert(note: Note)
    @Delete
    suspend  fun delete(note: Note)
    @Update
    suspend  fun update(note: Note)

    @Query("DELETE  FROM note_table")
    fun deleteAllNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY nums DESC")
    fun getAllNote():LiveData<MutableList<Note>>  //có thể quan sát đối tượng , khi có thay đổi sẽ được thông báo tự động



}