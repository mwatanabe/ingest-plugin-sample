This is elasticsearch plugin sample for Ingest Node Processor.

TypoReplaceProcessor fixes typos in a field value and set a new field `typo_count`.

# How to build
```
gradle build
```

# Setting
```
"processors": [
  {
    "typo": {
      "field": "foo",
      "target": "bar",
      "replacement": "baz"
    }
  }
]
```

# Transformation

before 
```json
{
  "foo": "bar bar bar"
}
```

after
```json
{
  "typo_count": 3,
  "foo": "baz baz baz"
}
```

# License
MIT License
