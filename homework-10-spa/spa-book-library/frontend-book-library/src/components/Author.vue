<template>
    <div>
        <button v-on:click="$emit('close-author')">Назад</button>
        <hr>
        <label>Имя автора: <input type="text" v-bind:readonly="!isEdit" v-model="author.name"></label>
        <div>
            <button v-if="!isEdit" v-on:click="enableEditMode">Редактировать</button>
            <button v-else v-on:click="saveAuthor">Сохранить</button>
        </div>
    </div>
</template>

<script>

    export default {
        name: "Author",
        props: {
            author: {
                type: Object,
                required: true
            },
            add: {
                type: Boolean,
                default() {
                    return false
                }
            }
        },
        data() {
            return {
                isEdit: this.add
            }
        },
        methods: {
            enableEditMode() {
                this.isEdit = true
            },
            saveAuthor() {
                this.$emit('save-author', this.author)
                this.isEdit = false
            }

        }
    }
</script>

<style scoped>

</style>