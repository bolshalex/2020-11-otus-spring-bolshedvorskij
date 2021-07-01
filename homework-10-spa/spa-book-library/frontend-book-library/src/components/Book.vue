<template>
    <li class="book-card">
        <div>Название: {{ book.bookTitle }}</div>
        <div>Авторы:
            <ul>
                <li v-for="author in book.authors"
                    v-bind:key="author.id">
                    {{ author.name }}
                </li>
            </ul>
        </div>
        <div>Жанры:
            <ul>
                <li v-for="genre in book.genres "
                    v-bind:key="genre.id">
                    {{ genre.name }}
                </li>
            </ul>
        </div>
        <button v-on:click="$emit('delete-book',book)">Удалить</button>
        <button v-on:click="$emit('edit-book',book)">Редактировать</button>
    </li>
</template>

<script>
    export default {
        props: {
            book: {
                Object,
                required: true
            }
        }
    }
</script>
<style scoped>

    .book-card {
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