<template>
    <div>
        <h2>Список книг</h2>
        <router-link to="/">Home</router-link>
        <hr>
        <BookList/>
    </div>
</template>

<script>
    import BookList from "../components/BookList";

    export default {
        name: 'App',
        components: {
            BookList
        }
    }
</script>