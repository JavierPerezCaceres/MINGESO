<template>
  <div class="text-center">
    <v-autocomplete
              v-model="select"
              :rules="[() => !!select || 'La carrera es requerida']"
              :items="items"
              label="Carrera"
              placeholder="Elige la carrera"
              required
              hide-no-data
            ></v-autocomplete>
  </div>
</template>