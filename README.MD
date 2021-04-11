
# React tutorial


React patří mezi nejpopulárnější knihovny pro tvorbu uživatelského rozhraní webových aplikací.

A proč se nenaučit něco nového?

Cílem workshopu je vytvořit mikro eshop, 
na kterém budou demonostrovány základy pro Reactu.

Eshop bude podporovat práci s produkty, 
jako je přidávání nového zboží, editace, mazání a výpis.
Další důležitou funkcionalitou eshopu je práce s nákupním košíkem.


[Výukové video](https://youtu.be/C4LoZjzqG_k)



Pro snadnější vývoj je vhodný mít nainstalované
- [Node.js](https://nodejs.org/en/download/)

Ověření instalace následujícími příkazy pro zjištění verze:

```javascript
node -v
```
```javascript
npm -v
```
```javascript
npx -v
```

### Základní příkazy

Instalace a spuštění nového projektu. React aplikace se spustí na portu [3000](http://localhost:3000).
```
npx create-react-app upce-shop
cd upce-shop
npm start
```


Start mock serveru na portu [3001](http://localhost:3001). Je nutno mít soubor `db.json`, který obsahuje datové struktury. 
Dále se servírují soubory z adresáře `./img`. Pozor na relativní cesty.
```
npx json-server --watch db.json --static ./img --port 3001
```


### Slovníček pojmů


#### Single-page aplikace

- zkráceně SPA 
- aplikace, která načte jedno HTML a další potřebné zdroje (obrázky, JavaScript, CSS)
- každá další interakce s aplikací  nepotřebuje udělat požadavek na server
- na server se dělají požadavky pouze na nezbytná data
- Server-Side-Rendering X Client-Side-Rendering

#### Komponenta

- vizuální prvek
- může mít v s sobě více komponent
- je znovupoužitelná (většinou se o to snažíme)

#### React komponenta

- výraz, která vrací `JSX`
- JSX je šablonovací jazyk pro psaní komponent
- na první pohled vypadá jako HTML
- lze v něm používat JavaScript (v zásadě ho rozšiřuje)

```javascript
const name = 'Mr. Component';
const element = (
  <h1>
    Hello, I am {name}!
  </h1>
);
```

#### React Hook

- funkce, které se spouští v určité fázi životního cyklu komponenty (změna vstupních parametrů)
- dostupné až od verze 16.8  (ještě se můžete setkat se zápisem tříd) 

#### [useState](https://reactjs.org/docs/hooks-state.html) 

- používá pro udržení stavu v komponentě 
- při změně stavu dojde k překreslení komponenty a všech jeho potomků
- hook

#### [useEffect](https://reactjs.org/docs/hooks-effect.html) 

- funkce, která se spouští v závislosti na změnách stavu v komponentě
- hook 

```javascript
import React, { useState } from 'react';

function Example() {
  // Declare a new state variable, which we'll call "count"
  const [count, setCount] = useState(0);

  return (
    <div>
      <p>You clicked {count} times</p>
      <button onClick={() => setCount(count + 1)}>
        Click me
      </button>
    </div>
  );
}
```

#### [fetch](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch) 

- funkce pro volání serveru

```javascript
fetch('http://example.com/movies.json')
  .then(response => response.json())
  .then(data => console.log(data));
```

#### [map](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/map) 

- funkce, která iteruje přes všechny prvky poli; 
pro každý prvek se očekává návratová hodnota; 
  typicky využití hodnot pro vytvoření komponenty v seznamu
- počet položek v novém poli je stejný jako počet položek ve vstupním poli 
- lze chápat jako `foreach`, ale položek v map musí vždy něco vracet
```javascript
const items = [2, 5, 6, 3, 8, 9];
          
var sqrtObjectItems = items.map((val, index) => {
  return {key:index, value:val*val};
})
```

#### [filter](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/filter) 

- používá se pro filtrování položek
- vrací vyfiltrovaný seznam

```javascript
const words = ['spray', 'limit', 'elite', 'exuberant', 'destruction', 'present'];

const result = words.filter(word => word.length > 6);
```


---
#### Zdroje
- https://reactjs.org/docs/
- https://developer.mozilla.org/en-US/docs/
