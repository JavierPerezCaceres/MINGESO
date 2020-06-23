<template>
  <v-container class="py-10">
    <v-row justify="center" align="center">
      <h1 class="text-title text--black pb-5">Equipo</h1>
    </v-row>

    <v-row justify="center" align="center">
      <v-img
        class="mx-5"
        max-height="600"
        max-width="660"
      ></v-img>
      <v-col cols="12">
        <v-row align="center" justify="center">
          <div v-for="person in team" :key="person.name" class="mx-auto">
            <v-card class="mx-1 custom-card-style" max-width="200">
              <v-card-text class="text-center">
                <div>{{person.job}}</div>
                <v-row justify="space-around" >
                  <v-img :src=" person.avatar " max-height="80" max-width="80"></v-img>
                </v-row>
                <br />
                <p class="display-1 text--primary">{{person.name}}</p>
              </v-card-text>
            </v-card>
            <v-spacer class="py-3"></v-spacer>
          </div>
        </v-row>
      </v-col>
    </v-row>
    <v-img :src="require('@/assets/imgs/divider.svg')" max-height="50" max-width="500"></v-img>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      team: [
        {
          name: "Javier Pérez ",
          avatar:
            "https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortFlat&accessoriesType=Blank&hairColor=Brown&facialHairType=Blank&clotheType=Hoodie&clotheColor=Black&eyeType=Default&eyebrowType=Default&mouthType=Smile&skinColor=Pale",
          job: ""
        },
        {
          name: "Roberto Lillo",
          avatar:
            "https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortFlat&accessoriesType=Blank&hairColor=BlondeGolden&facialHairType=Blank&clotheType=Hoodie&clotheColor=Blue01&eyeType=Default&eyebrowType=Default&mouthType=Serious&skinColor=Pale",
          job: ""
        }
      ]
    };
  }
};
</script>

<style>
.custom-card-style {
  transition: background 0.2s ease-out !important;
}
.custom-card-style:hover {
  background: #d9d9d9 !important;
}
</style>