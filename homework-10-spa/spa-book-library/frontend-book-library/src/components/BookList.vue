<template>
    <div>
        <hr>
        <Loader v-if="loading"/>
        <div v-else>
            <BookEditor v-if="isEditBook"
                        v-bind:book="editableBook"
                        v-on:save-book="saveBook"></BookEditor>

            <ul v-else class="book-list">
                <Book v-for="book in books"
                      v-bind:book="book"
                      v-bind:key="book.id"
                      v-on:edit-book="editBook"
                      v-on:delete-book="deleteBook"
                />
                <li class="add-card">
                    <button v-on:click="addNewBook">Добавить</button>
                </li>
            </ul>
            <hr>
        </div>
    </div>
</template>

<script>
    import Book from '@/components/Book'
    import BookEditor from '@/components/BookEditor'
    import Loader from '@/components/Loader'


    export default {
        components: {
            Book,
            BookEditor,
            Loader
        },
        data() {
            return {
                books: [],
                loading: true,
                isEditBook: false,
                editableBook: {
                    type: Object
                }
            }
        },
        methods: {
            saveBook(book) {
                console.log("Сохранение/добавление книги: " + JSON.stringify(book))
                if (book.bookId) {
                    const requestOptions = {
                        method: 'PUT',
                        body: JSON.stringify(book),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                    fetch("/api/books", requestOptions)
                    console.log("Сохранение книги " + requestOptions.body)
                } else {
                    const requestOptions = {
                        method: 'POST',
                        body: JSON.stringify(book),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                    fetch("/api/books", requestOptions)
                    console.log("Добавление книги " + requestOptions.body)
                }
                this.isEditBook = false;
                this.editableBook = null
                this.loadBooks()
            },
            addNewBook() {
                this.editableBook = {id: '', bookTitle: '', authors: [], genres: []}
                this.isEditBook = true
            },
            editBook(book) {
                this.isEditBook = true
                this.editableBook = book;
            },

            deleteBook(book) {
                fetch("/api/books/" + book.bookId, {method: 'DELETE'})
                    .catch(reason => console.log("Error delete by book.id= " + book.id + " " + reason))
                this.loadBooks()
            },

            loadBooks() {
                this.loading = true

                fetch("/api/books")
                    .then(response => response.json())
                    .then(data => (this.books = data));

                this.loading = false
            }
        },

        mounted() {
            this.loadBooks()
        }

    }

</script>

<style scouped>
    ul {
        list-style: none;
    }

    .book-list {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        padding: 14px 0 0;
        margin: 0 0 0 -20px;
        list-style: none;
    }

    .add-card {
        position: relative;
        width: 500px;
        height: auto;
        margin-bottom: 20px;
        margin-left: 20px;
        padding: 12px 12px 12px;
        font-family: "Georgia", "Times", serif;
        background-color: #ffffff;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    }
</style>