<template>
  <v-form id="generalForm" ef="form" v-model="valid" ref="form">
    <v-row align="center" justify="center">
      <v-col class="text-center" cols="5" sm="2">
        <v-text-field v-model="firstName" :rules="nameRules" label="Nombre postulante" required></v-text-field>
      </v-col>

      <v-col class="text-center" cols="5" sm="2">
        <v-text-field
          v-model="apellido"
          :rules="lastNameRules"
          label="Apellido postulante"
          required
        ></v-text-field>
      </v-col>

      <v-col class="text-center" cols="5" sm="2">
        <v-text-field v-model="rut" :rules="rutRule" label="Rut" required></v-text-field>
      </v-col>

      <v-col class="text-center" cols="5" sm="2">
        <v-menu
          ref="menuDate"
          v-model="menuDate"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="290px"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              v-model="dateFormatted"
              label="Fecha de nacimiento"
              hint="En formato : dd-mm-aaaa"
              persistent-hint
              readonly
              v-on="on"
              :rules="dateRules"
              required
            ></v-text-field>
          </template>
          <v-date-picker v-model="date" no-title @input="menuDate = false"></v-date-picker>
        </v-menu>
      </v-col>

      <v-col class="text-center" cols="5" sm="2">
        <v-autocomplete
          v-model="career"
          :items="career"
          label="Carrera"
          placeholder="Elige la carrera"
          required
          :rules="[v => !!v || 'La carrera es requerida']"
          hide-no-data
        ></v-autocomplete>
      </v-col>
    </v-row>

    <v-row align="center" justify="center">
      <div class="my-2">
        <v-btn :disabled="!valid" color="primary" class="mr-4" @click="sendStudent();">Enviar</v-btn>
      </div>
    </v-row>

    <br />
    <br />
    <br />

    <v-row justify="center">
      <div>
        <DataTable />
      </div>
    </v-row>
    <v-row justify="center">
      <div>
        <Team />
      </div>
    </v-row>
  </v-form>
</template>

<script>
import http from "@/plugins/http-common";
import DataTable from "./DataTable";
import Team from "./Team";
import dataUsach from "./data/careers.json";

export default {
  components: {
    DataTable,
    Team
  },
  data: vm => ({
    date: new Date().toISOString().substr(0, 10),
    dateFormatted: vm.formatDate(new Date().toISOString().substr(0, 10)),
    valid: true,
    menuDate: false,
    firstName: "",
    lastName: "",
    apellido: "",
    career: dataUsach.careers,
    nameRules: [v => !!v || "El nombre es requerido"],
    lastNameRules: [v => !!v || "El apellido es requerido"],
    rut: "",
    rutRule: [

      //^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$

      v => !!v || "El rut es requerido",
      v =>
        /^([0-9]+)-[0-9kK]$/.test(v) ||
        "El rut debe ser valido (XXXXXXXXX-X)"
    ],
    dateRules: [v => !!v || "La fecha de nacimiento es requerida"],
    response: "",
    careers: []
  }),
  computed: {
    computedDateFormatted() {
      return this.formatDate(this.date);
    }
  },
  watch: {
    date() {
      this.dateFormatted = this.formatDate(this.date);
    }
  },
  methods: {
    formatDate(date) {
      if (!date) return null;

      const [year, month, day] = date.split("-");
      return `${day}-${month}-${year}`;
    },
    parseDate(date) {
      if (!date) return null;

      const [month, day, year] = date.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    },
    sendStudent() {

      var studentData = {
        name: this.firstName,
        lastname: this.apellido,
        rut: this.rutKUpper(this.rut),
        birthDate: this.dateFormatted,
        career: this.career
      };
      http
        .post("/students/create", studentData)
        .then(response => {
          console.log(response);
          alert("El estudiante ha sido ingresado con exito.");
          this.reloadPage();
        })
        .catch(e => {
          console.log(e);
          alert("No se ha podido ingresar el estudiante.");
        });
    },
    getCareers() {
      http
        .get("/careers")
        .then(response => {
          this.response = response.data; // JSON are parsed automatically.
          var i = 0;
          while (i < response.data.length) {
            this.careers.push(response.data[i].name);
            i += 1;
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    reloadPage() {
      window.location.reload();
    },
    rutKUpper(rut){
      if(rut.charAt(rut.toString().length - 1) == "k"){
        return rut.toString().substring(0, 9).concat("K");
      }
      else{
        return rut
      }
    }
  }
};
</script>