USE [Challengifier]
GO

CREATE TABLE [dbo].[PlanningStep](
	[PlanningStep_ID] [uniqueidentifier] NOT NULL,
	[StartDate] [date] NOT NULL,
	[EndDate] [date] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](250) NULL,
	[Challenge_ID] [uniqueidentifier] NOT NULL,
	PRIMARY KEY ([PlanningStep_ID]))