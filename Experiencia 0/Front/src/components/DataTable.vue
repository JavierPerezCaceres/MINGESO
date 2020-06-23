<template>
  <v-form id="dataTableForm" ef="form" v-model="valid">
    <v-card>
      <v-card-title>
        Estudiantes
        <v-spacer></v-spacer>
      </v-card-title>
      <v-data-table :headers="headers" :items-per-page="10" :items="students" class="elevation-1"></v-data-table>
    </v-card>
  </v-form>
</template>

<script>
import http from "@/plugins/http-common";

export default {
  data() {
    return {
      headers: [
        { text: "Nombre", value: "name" },
        { text: "Apellido", value: "lastname" },
        { text: "Rut", value: "rut" },
        { text: "Fecha de Nacimiento ", value: "birthDate" },
        { text: "Carrera", value: "career" }
      ],
      students: []
    };
  },
  methods: {
    getStudents() {
      http
        .get("/students")
        .then(response => {
          this.response = response.data; // JSON are parsed automatically.
          var i = 0;
          while (i < response.data.length) {
            var year = this.response[i].birthDate.substring(
              0,
              4
            );
            var month = this.response[i].birthDate.substring(
              5,
              7
            );
            var day = this.response[i].birthDate.substring(
              8,
              10
            );
            this.response[i].birthDate = day + "-" + month + "-" + year;
            this.students.push(this.response[i]);
            i += 1;
          }
        });
    }
  },
  mounted() {
    this.getStudents();
  }
};
</script>