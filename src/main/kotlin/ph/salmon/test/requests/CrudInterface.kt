package ph.salmon.test.requests

interface CrudInterface<T> {
    fun create(item: T): T
    fun read(id: Int): T
    fun update(id: Int, item: T): T
    fun delete(id: Int): String
}