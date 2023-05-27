<template>
<b-card header-tag="header">
    <template #header>
        Login
    </template>

    <b-card-body>
        <b-form inline @submit="onSubmit">

<b-container fluid>
    <b-row class="my-1">
        <b-form-input v-model="username" />
    </b-row>
    <b-row class="my-1">
        <b-form-input v-model="password" type="password" />
    </b-row>

    <b-row class="my-1">
        <b-col sm="3">
        </b-col>
        <b-col sm="9">
        <b-button type="submit" variant="primary">Login</b-button>
        </b-col>
    </b-row>
</b-container>
</b-form>

<b-alert variant="danger" :show="showError" dismissible>{{error}}</b-alert>
</b-card-body>
</b-card>
</template>


<script lang="ts">

import {checkLoggedUser, login} from "../framework/authentication";

export default {
  components: { },
    data()  {
        return {
            username: "",
            password: "",
            showError: false,
            error:""
        }
    },
    methods: {
        onSubmit(): void {
            login(this.username, this.password).catch( (reason)=>{
                this.error=reason;
                this.showError=true;
            });
        }
    },
    mounted() {
        checkLoggedUser().catch( (reason)=>{
                this.error=reason;
                this.showError=true;
            });
    }

}
</script>
