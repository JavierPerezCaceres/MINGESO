<template>
  <v-menu
    v-model="birthDate"
    :close-on-content-click="false"
    transition="scale-transition"
    max-width="290px"
    min-width="290px"
  >
    <template v-slot:activator="{ on }">
      <v-text-field
        v-model="computedDateFormatted"
        label="Fecha de nacimiento"
        readonly
        v-on="on"
      ></v-text-field>
    </template>
    <v-date-picker v-model="birthDate" no-title @input="birthDate = false"></v-date-picker>
  </v-menu>
</template>


<script>
export default {
  data: vm => ({
    date: new Date().toISOString().substr(0, 10),
    dateFormatted: vm.formatDate(new Date().toISOString().substr(0, 10)),
    menu1: false,
    menu2: false
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
      return `${month}/${day}/${year}`;
    },
    parseDate(date) {
      if (!date) return null;

      const [month, day, year] = date.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    }
  }
};
</script>