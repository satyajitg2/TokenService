use [TOKEN_GVA_SIT] 
go

--TokenDateTime
CREATE INDEX TD_INDEX
ON [vault].[TokenDateTime] (RepeatableFlag)

CREATE INDEX TI_INDEX
ON [vault].[TokenInteger] (RepeatableFlag)

CREATE INDEX TS_INDEX
ON [vault].[TokenString] (RepeatableFlag)