<template>
    <div>
        <Author v-if="author !== null"
                v-bind:author="author"
                v-bind:add="author.id === null"
                v-on:save-author="saveAuthor"
                v-on:close-author="closeAuthor"/>
        <div v-else>
            <Loader v-if="loading"/>
            <ul v-else>
                <li v-for="author in authors"
                    v-bind:key="author.id">
                    <a href="#" class="author-row" v-on:click="viewAuthor(author)">{{ author.name }}</a>
                    <button v-on:click="deleteAuthor(author)"> Удалить</button>
                </li>
                <li>
                    <button v-on:click="addAuthor">Добавить</button>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
    import Loader from '../components/Loader'
    import Author from '../components/Author'

    export default {
        components: {
            Loader,
            Author
        },
        name: "AuthorList",

        data() {
            return {
                loading: false,
                authors: [],
                author: null
            }
        },

        methods: {
            loadAuthors() {
                this.loading = true
                fetch('/api/authors')
                    .then(response => response.json())
                    .then(data => (this.authors = data))

                this.loading = false
            },
            viewAuthor(author) {
                this.author = author
            },

            async saveAuthor(author) {
                if (author.id) {
                    const requestOptions = {
                        method: 'PUT',
                        body: JSON.stringify(author),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                    await fetch("/api/authors/" + author.id, requestOptions)
                        .catch(reason => alert("произошла ошибка при изменении автора: ") + reason)

                } else {
                    const requestOptions = {
                        method: 'POST',
                        body: JSON.stringify(author),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                    await fetch("/api/authors", requestOptions)
                        .catch(reason => alert("произошла ошибка при добавлении автора: " + reason))
                }
                this.author = null
                this.loadAuthors()
            },
            addAuthor() {
                this.author = {id: null, name: ''}
            },
            async deleteAuthor(author) {
                await fetch("/api/authors/" + author.id, {method: 'DELETE'})
                    .catch(reason => console.log("Error delete by author.id= " + author.id + " " + reason))
                this.loadAuthors()

            },
            closeAuthor() {
                this.author = null
            }
        },
        mounted() {
            this.loadAuthors()
        }
    }
</script>

<style scoped>
    ul {
        list-style: none;
    }

    .author-row {
        margin-right: 10px;
    }
</style>