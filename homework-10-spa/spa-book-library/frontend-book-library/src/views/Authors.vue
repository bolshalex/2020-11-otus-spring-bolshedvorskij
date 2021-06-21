<template>
    <div>
        <h2>Список авторов</h2>
        <router-link to="/">Home</router-link>
        <hr>
        <AuthorList/>
    </div>
</template>

<script>
    import AuthorList from '../components/AuthorList'

    export default {
        name: "Authors.vue",
        components: {
            AuthorList
        }
    }
</script>

<style scoped>

</style>