<template>
    <div>
        <label> Название книги
            <input type="text" v-model="book.bookTitle">
        </label>
        <hr>
        <label>Поиск автора по имени
            <input type="text" v-model="searchingAuthor" v-on:input="searchAuthors"/>
        </label>

        <ul>
            <li class="selectedItem" v-for="author in foundAuthors"
                v-bind:key="author.id">
                <span>{{ author.name }}</span>
                <button v-on:click="addToBookAuthor(author)">Добавить</button>
            </li>
        </ul>
        <p>Авторы книги:</p>
        <div v-if="book.authors">
            <ul>
                <li class="selectedItem" v-for="author in book.authors"
                    v-bind:key="author.id">
                    <span>{{ author.name }}</span>
                    <button v-on:click="deleteBookAuthor(author)">Удалить</button>
                </li>
            </ul>
        </div>
        <p v-else>Авторы еще добавлены </p>
        <hr>
        <label>Поиск жанра
            <input type="text" v-model="searchingGenre" v-on:input="searchGenres"/>
        </label>
        <ul>
            <li class="selectedItem" v-for="genre in foundGenres"
                v-bind:key="genre.id">
                <span>{{ genre.name }}</span>
                <button v-on:click="addToBookGenre(genre)">Добавить</button>
            </li>
        </ul>
        <p>Жанры:</p>
        <div v-if="book.genres">
            <ul>
                <li class="selectedItem" v-for="genre in book.genres"
                    v-bind:key="genre.id">
                    <span>{{ genre.name }}</span>
                    <button v-on:click="deleteBookGenre(genre)">Удалить</button>
                </li>
            </ul>
        </div>
        <p v-else>Жанры еще не добавлены</p>
        <hr>
        <button v-on:click="saveBook">Сохранить</button>
    </div>
</template>

<script>
    import _ from 'lodash'

    export default {
        props: {
            book: {
                type: Object,
                default: function () {
                    return {
                        bookid: '',
                        bookTitle: '',
                        authors: [],
                        genres: []
                    }
                }
            }
        },
        data() {
            return {
                searchingAuthor: '',
                searchingGenre: '',

                foundAuthors: [],
                foundGenres: []
            }
        },
        methods: {
            searchAuthors: _.debounce(function (e) {
                if (e.target.value.length) {
                    console.log("Get authors by name " + e.target.value)

                    fetch("/api/authors?name=" + e.target.value)
                        .then(response => response.json())
                        .then(data => this.foundAuthors = data)
                }
            }, 1000, {leading: false, trailing: true}),

            addToBookAuthor(author) {
                console.log("Добавление автора в книгу " + JSON.stringify(author))
                this.book.authors.push(author)
                this.foundAuthors = this.foundAuthors.filter(a => a.id !== author.id)
            }
            ,
            deleteBookAuthor(author) {
                this.book.authors = this.book.authors.filter(a => a.id !== author.id)
            }
            ,

            searchGenres: _.debounce(function (e) {
                if (e.target.value.length) {
                    console.log("Get genres by name " + e.target.value)
                    fetch("/api/genres?name=" + e.target.value)
                        .then(response => response.json())
                        .then(data => this.foundGenres = data)
                }
            }, 1500, {leading: false, trailing: true})
            ,
            addToBookGenre(genre) {
                this.book.genres.push(genre)
                this.foundGenres = this.foundGenres.filter(g => g.id !== genre.id)
            }
            ,
            deleteBookGenre(genre) {
                this.book.genres = this.book.genres.filter(g => g.id !== genre.id)
            }
            ,

            saveBook() {
                this.$emit('save-book', this.book)
            }

        }
    }
</script>

<style scoped>
    ul {
        list-style: none;
        margin: 0;
        padding: 0;
    }

    .selectedItem {
        horiz-align: left;
        border: 1px solid #cccccc;
        display: flex;
        justify-content: space-between;
        padding: .5rem 2rem;
        margin-bottom: 1rem;
    }
</style>