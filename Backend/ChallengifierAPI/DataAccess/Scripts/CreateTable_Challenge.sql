USE [Challengifier]
GO
CREATE TABLE [dbo].[Challenge](
	[Challenge_ID] [uniqueidentifier] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](250) NOT NULL,
	[Suggested_Time_Interval] [nvarchar](50) NULL,
	[Pictures] [varbinary](max) NULL,
	PRIMARY KEY ([Challenge_ID]))
GO

use Challengifier 
go
ALTER TABLE Objective
ADD FOREIGN KEY (Challenge_ID)
REFERENCES Challenge(Challenge_ID)